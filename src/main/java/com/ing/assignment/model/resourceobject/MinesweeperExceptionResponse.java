package com.ing.assignment.model.resourceobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data
class MinesweeperExceptionResponse {

    private int httpCode;
    private String httpStatus;
    private String message;
}
