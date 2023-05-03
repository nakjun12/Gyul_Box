package jeju.oneroom.houseInfo.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
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

    public List<HouseInfoDto.SimpleCountResponse> findAreaHouseInfos(Area area, int level) {
        return level >= 9 ? houseInfoRepository.findTop20ByAreaOrderByReviewCount(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList())
                : houseInfoRepository.findByArea(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList());
    }

    public HouseInfo findVerifiedHouseInfo(long houseInfoId) {
        return houseInfoRepository.findById(houseInfoId).orElseThrow(() -> new RuntimeException("HOUSEINFO_NOT_FOUND"));
    }

    public HouseInfo findVerifiedHouseInfoByAddress(String address) {
        return houseInfoRepository.findByPlatPlc(address).orElseThrow(() -> new RuntimeException("HOUSEINFO_NOT_FOUND"));
    }
}
