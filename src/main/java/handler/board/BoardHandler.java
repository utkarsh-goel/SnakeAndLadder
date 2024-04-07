package handler.board;

import exception.InvalidOccupantException;
import model.Board;

/**
 * Interface defining the snake and ladder board
 */
public interface BoardHandler {

    Board initializeBoard() throws InvalidOccupantException;

    void printBoard(final Board board);
}
