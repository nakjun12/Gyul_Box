package jeju.oneroom.town.dto;

import jeju.oneroom.common.entity.Coordinate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TownDto {
    public static class Response{
        private Long townCode;
        private String townName;
        private Coordinate coordinate;
    }
}
