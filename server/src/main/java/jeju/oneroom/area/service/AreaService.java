package jeju.oneroom.area.service;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.mapper.AreaMapper;
import jeju.oneroom.area.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreaService {
    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    public AreaDto.Response findArea(long areaCode){
        Area area = areaRepository.findById(areaCode).orElse(null);
        return areaMapper.areaToResponseDto(area);
    }
}
