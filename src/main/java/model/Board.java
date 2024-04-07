package model;

import constants.BoardType;
import lombok.Builder;
import lombok.Data;
import model.cellOccupant.CellOccupant;

import java.util.HashMap;

@Data
@Builder
public class Board {
    BoardType boardType;
    int length;
    int breadth;
    HashMap<Integer, CellOccupant> boardLayout;
}
