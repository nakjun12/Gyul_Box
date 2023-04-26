package jeju.oneroom.area.service;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreaService {
    private final AreaRepository areaRepository;

    public Area findArea(long areaCode){
        return findArea(areaCode);
    }
}
