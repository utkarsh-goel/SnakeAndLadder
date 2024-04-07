package utils;

import exception.InvalidOccupantException;
import lombok.experimental.UtilityClass;
import model.Board;
import model.cellOccupant.NoOccupant;
import model.cellOccupant.Ladder;
import model.cellOccupant.CellOccupant;
import model.cellOccupant.Snake;

import java.util.HashMap;

/**
 * Utility class for populating snake-and-ladders board
 */
@UtilityClass
public class PopulateBoardUtil {

    int MIN_POS_TO_ADD_ELEMENTS = 2;

    /**
     * Populates the board with snakes and ladders
     * @param board : Map of cell number to occupant
     * @throws InvalidOccupantException : In case occupant of a cell is invalid
     */
    public void populateSnakesAndLadders(final Board board) throws InvalidOccupantException {
        final int totalNumOfCells = board.getLength() * board.getBreadth();
        final HashMap<Integer, CellOccupant> cells = board.getBoardLayout();
        for (int cellNum = 0; cellNum <= totalNumOfCells; cellNum++) {
            cells.put(cellNum, new NoOccupant(cellNum, cellNum));
        }
        for (int count = 0; count < Math.sqrt(totalNumOfCells); count++) {
            int min = MIN_POS_TO_ADD_ELEMENTS;
            int max = totalNumOfCells - 1;
            int start = getRandomNumberInBetween(min, max);
            int end = getRandomNumberInBetween(min, max);
            if (areCellsVacant(board.getBoardLayout(), start, end)) {
                if (start < end) {
                    cells.put(start, new Ladder(start, end));
                    cells.put(end, new Ladder(start, end));
                } else if (start > end) {
                    cells.put(start, new Snake(start, end));
                    cells.put(end, new Snake(start, end));
                }
            }
        }
        board.setBoardLayout(cells);
    }

    private boolean areCellsVacant(final HashMap<Integer, CellOccupant> cells, int startPosition, int endPosition) {
        return (cells.get(startPosition) instanceof NoOccupant) && (cells.get(endPosition) instanceof NoOccupant);
    }

    private int getRandomNumberInBetween(final int min, final int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
