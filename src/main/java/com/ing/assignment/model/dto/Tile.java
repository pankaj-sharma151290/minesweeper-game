package com.ing.assignment.model.dto;

import com.ing.assignment.enums.TileState;
import com.ing.assignment.enums.TileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
class Tile {
    private int rowIndex, colIndex;
    private int nMinesAround;
    private TileType type;
    private TileState state;

    public Tile(int rowIndex, int colIndex) {
        this.rowIndex = colIndex;
        this.colIndex = rowIndex;
        this.type = TileType.EMPTY;
        this.state = TileState.COVERED;
    }

    public boolean isCovered() {
        return (this.state == TileState.COVERED || this.state == TileState.FLAGGED);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Tile)) {
            return false;
        }
        Tile obj = (Tile) object;
        return (this.rowIndex == obj.rowIndex && this.colIndex == obj.colIndex);
    }

}
