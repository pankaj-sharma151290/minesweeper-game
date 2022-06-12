package com.ing.assignment.model.resourceobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data
class GameRequestRO {
    private int nCols, nRows, nMines;
}
