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
    private final AreaRepository areaRepository;

    private final HouseInfoMapper houseInfoMapper;

    public HouseInfoDto.Response findHouseInfo(long houseInfoId){
        HouseInfo houseInfo = houseInfoRepository.findById(houseInfoId).orElse(null);
        return houseInfoMapper.houseInfoToResponseDto(houseInfo);
    }

    public HouseInfoDto.SimpleResponse findHouseInfoSimple(long houseInfoId){
        HouseInfo houseInfo = houseInfoRepository.findById(houseInfoId).orElse(null);
        return houseInfoMapper.houseInfoToSimpleResponseDto(houseInfo);
    }

    public List<HouseInfoDto.SimpleCountResponse> findAreaHouseInfos(long areaCode, int level){
        Area area = areaRepository.findById(areaCode).orElse(null);
        return level >= 9 ? houseInfoRepository.findTop20ByAreaOrderByReviewCount(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList())
                : houseInfoRepository.findByArea(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList());
    }
}
