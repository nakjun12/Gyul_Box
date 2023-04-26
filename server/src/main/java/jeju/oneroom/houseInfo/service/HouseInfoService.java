package jeju.oneroom.houseInfo.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HouseInfoService {
    private final HouseInfoRepository houseInfoRepository;
    private final AreaRepository areaRepository;

    public HouseInfo findHouseInfo(long houseInfoId){
        return houseInfoRepository.findById(houseInfoId).orElse(null);
    }

    public HouseInfo findHouseInfoSimple(long houseInfoId){
        return houseInfoRepository.findById(houseInfoId).orElse(null);
    }

    public List<HouseInfo> findHouseInfoSimple(long areaCode, int level){
        Area area = areaRepository.findById(areaCode).orElse(null);
        return houseInfoRepository.findByArea(area);
    }
}
