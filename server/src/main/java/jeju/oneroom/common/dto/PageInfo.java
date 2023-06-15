package jeju.oneroom.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 페이지 정보
@AllArgsConstructor
@Getter
public class PageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
