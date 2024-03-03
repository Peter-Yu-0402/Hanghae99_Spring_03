package com.sparta.lv3backoffice.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    private boolean status;
    private String message;
    private T data;
}
