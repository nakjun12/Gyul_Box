package jeju.oneroom.houseInfo.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.mapper.HouseInfoMapper;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HouseInfoService {
    private final HouseInfoRepository houseInfoRepository;

    private final HouseInfoMapper houseInfoMapper;

    public HouseInfoDto.Response findHouseInfo(long houseInfoId) {
        return houseInfoMapper.houseInfoToResponseDto(findVerifiedHouseInfo(houseInfoId));
    }

    public HouseInfoDto.SimpleResponse findHouseInfoSimple(long houseInfoId) {
        return houseInfoMapper.houseInfoToSimpleResponseDto(findVerifiedHouseInfo(houseInfoId));
    }

    public List<HouseInfoDto.SimpleCountResponse> findHouseInfosByArea(Area area, int level) {
        return level >= 9 ? houseInfoRepository.findTop20ByAreaOrderByReviewCount(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList())
                : houseInfoRepository.findByArea(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList());
    }

    public List<HouseInfoDto.SimpleContentResponse> findHouseInfosByContent(String content) {
        return houseInfoRepository.findByPlatPlcContains(content).stream().map(houseInfoMapper::houseInfoToSimpleContentResponseDto).collect(Collectors.toList());
    }

    public void updateHouseInfoRate(HouseInfo houseInfo, Rate rate) {
        double reviewCount = houseInfo.getReviewCount();
        Rate newRate = Rate.builder()
                .buildingRate(Math.round((1 / reviewCount * rate.getBuildingRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getBuildingRate()) * 1000) / 100.0)
                .securityRate(Math.round((1 / reviewCount * rate.getSecurityRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getSecurityRate()) * 1000) / 100.0)
                .interiorRate(Math.round((1 / reviewCount * rate.getInteriorRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getInteriorRate()) * 1000) / 100.0)
                .locationRate(Math.round((1 / reviewCount * rate.getLocationRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getLocationRate()) * 1000) / 100.0)
                .trafficRate(Math.round((1 / reviewCount * rate.getTrafficRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getTrafficRate()) * 1000) / 100.0)
                .build();
        houseInfo.updateRate(newRate);
    }

    public HouseInfo findVerifiedHouseInfo(long houseInfoId) {
        return houseInfoRepository.findById(houseInfoId).orElseThrow(() -> new RuntimeException("HOUSEINFO_NOT_FOUND"));
    }
}
