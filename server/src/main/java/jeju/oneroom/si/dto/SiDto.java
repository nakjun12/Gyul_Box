package jeju.oneroom.si.dto;

import jeju.oneroom.common.entity.Coordinate;
import lombok.*;

@ToString
@Builder
@Getter
public class SiDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long siCode;
        private String siName;
        private Coordinate coordinate;
    }
}
