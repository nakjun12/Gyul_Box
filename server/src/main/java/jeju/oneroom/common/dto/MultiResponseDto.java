package jeju.oneroom.common.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

// Page가 첨가된 Response 객체
@Getter
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(Page<T> page) {
        this.data = page.getContent();
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}