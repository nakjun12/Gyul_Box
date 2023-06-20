package jeju.oneroom.houseInfo.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.exception.BusinessLogicException;
import jeju.oneroom.exception.ExceptionCode;
import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.mapper.HouseInfoMapper;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HouseInfoService {
    private final HouseInfoRepository houseInfoRepository;
    private final HouseInfoMapper houseInfoMapper;

    // 건물 정보 단일 조회
    public HouseInfoDto.Response findHouseInfo(long houseInfoId) {
        return houseInfoMapper.houseInfoToResponseDto(findVerifiedHouseInfo(houseInfoId));
    }

    // 지도의 노드 선택 시 단순 건물 정보 제공
    public HouseInfoDto.SimpleResponse findHouseInfoSimple(long houseInfoId) {
        return houseInfoMapper.houseInfoToSimpleResponseDto(findVerifiedHouseInfo(houseInfoId));
    }

    // 지도에서 노드에 대한 정보 요청
    public List<HouseInfoDto.SimpleCountResponse> findHouseInfosByArea(Area area, int level) {
        /*
        level >= 9 -> 해당 시의 review 추천순 top 20에 해당하는 houseInfo 제공
        level <= 8 -> 해당 동의 모든 houseInfo 제공
        */
        return level >= 9 ? houseInfoRepository.findTop20ByAreaOrderByReviewCount(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList())
                : houseInfoRepository.findByArea(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList());
    }

    // 건물 주소를 통한 조뢰
    public List<HouseInfoDto.SimpleContentResponse> findHouseInfosByContent(String content) {
        return houseInfoRepository.findByPlatPlcContains(content).stream().map(houseInfoMapper::houseInfoToSimpleContentResponseDto).collect(Collectors.toList());
    }

    // HouseInfo에 해당하는 Review가 없을 시 해당 Review의 별점이 HouseInfo의 별점이 됨
    // Review가 존재할 경우 HouseInfo의 Rate와 평균값으로 갱신
    @Transactional
    public void checkRate(HouseInfo houseInfo, ReviewDto.Post postDto) {
        getOptionalRate(houseInfo).ifPresentOrElse(rate -> updateHouseInfoRate(houseInfo, postDto.getRate())
                , () -> houseInfo.updateRate(postDto.getRate()));
    }

    // 리뷰 생성에 따른 건물 정보 별점 업데이트
    @Transactional
    public void updateHouseInfoRate(HouseInfo houseInfo, Rate rate) {
        double reviewCount = houseInfo.getReviewCount();
        Rate newRate = getRate(houseInfo, rate, reviewCount);
        houseInfo.updateRate(newRate);
    }

    // 유효한 건물 정보 확인
    public HouseInfo findVerifiedHouseInfo(long houseInfoId) {
        return houseInfoRepository.findById(houseInfoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_HOUSE_INFO));
    }

    // 건물 정보에 들어있는 별점의 평균과 새로 기입된 리뷰의 별점 조합
    private Rate getRate(HouseInfo houseInfo, Rate rate, double reviewCount) {
        return Rate.builder()
                .buildingRate(Math.round((1 / reviewCount * rate.getBuildingRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getBuildingRate()) * 100) / 100.0)
                .securityRate(Math.round((1 / reviewCount * rate.getSecurityRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getSecurityRate()) * 100) / 100.0)
                .interiorRate(Math.round((1 / reviewCount * rate.getInteriorRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getInteriorRate()) * 100) / 100.0)
                .locationRate(Math.round((1 / reviewCount * rate.getLocationRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getLocationRate()) * 100) / 100.0)
                .trafficRate(Math.round((1 / reviewCount * rate.getTrafficRate() + (reviewCount - 1) / reviewCount * houseInfo.getRate().getTrafficRate()) * 100) / 100.0)
                .build();
    }

    // Rate가 null인 경우 확인
    public Optional<Rate> getOptionalRate(HouseInfo houseInfo) {
        return Optional.ofNullable(houseInfo.getRate());
    }
}
