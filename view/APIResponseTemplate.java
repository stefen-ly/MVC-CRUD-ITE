package view;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record APIResponseTemplate<T>(
        Integer status,
        String message,
        LocalDate timeStamp,
        T data
) {
}
