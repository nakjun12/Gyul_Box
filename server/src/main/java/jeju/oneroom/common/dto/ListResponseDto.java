package jeju.oneroom.common.dto;

import lombok.Getter;

import java.util.List;

// 리스트 형식 Response 객체
@Getter
public class ListResponseDto<T> {
    private List<T> data;

    public ListResponseDto(List<T> data) {
        this.data = data;
    }
}