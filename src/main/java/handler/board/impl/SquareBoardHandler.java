package handler.board.impl;

import constants.BoardType;
import exception.InvalidOccupantException;
import handler.board.BoardHandler;
import model.Board;
import model.cellOccupant.CellOccupant;
import org.apache.commons.lang3.math.NumberUtils;
import utils.PopulateBoardUtil;

import java.util.HashMap;
import java.util.Scanner;

public class SquareBoardHandler implements BoardHandler {

    @Override
    public Board initializeBoard() throws InvalidOccupantException {
        final Scanner scanner = new Scanner(System.in);
        System.out.println(" =========== Enter the size of the SQUARE board =========== ");
        final int boardSize = scanner.nextInt();
        final HashMap<Integer, CellOccupant> cells = new HashMap<>();
        final Board board = Board.builder()
                .length(boardSize)
                .breadth(boardSize)
                .boardType(BoardType.SQUARE)
                .boardLayout(cells)
                .build();
        PopulateBoardUtil.populateSnakesAndLadders(board);
        System.out.println(" =========== SQUARE board initialized =========== ");
        return board;
    }

    /**
     * Prints the board
     */
    @Override
    public void printBoard(final Board board) {
        final int totalCells = board.getLength() * board.getBreadth();
        for (int currCell = totalCells; currCell >= NumberUtils.INTEGER_ONE; currCell--) {
            if (currCell % board.getLength() == 0) {
                System.out.println();
            }
            board.getBoardLayout().get(currCell).print();
        }
        System.out.println();
    }
}
