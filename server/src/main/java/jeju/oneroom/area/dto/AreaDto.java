package jeju.oneroom.area.dto;

import jeju.oneroom.common.entity.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AreaDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long areaCode;
        private String areaName;
        private Coordinate coordinate;
    }
}
