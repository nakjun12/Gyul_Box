package jeju.oneroom.si.dto;

import jeju.oneroom.common.entity.Coordinate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SiDto {
    public static class Response{
        private Long siCode;
        private String siName;
        private Coordinate coordinate;
    }
}
