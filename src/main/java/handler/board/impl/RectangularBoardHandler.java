package handler.board.impl;

import constants.BoardType;
import exception.InvalidOccupantException;
import handler.board.BoardHandler;
import model.Board;
import org.apache.commons.lang3.math.NumberUtils;
import utils.PopulateBoardUtil;

import java.util.HashMap;
import java.util.Scanner;

public class RectangularBoardHandler implements BoardHandler {

    @Override
    public Board initializeBoard() throws InvalidOccupantException {
        final Scanner scanner = new Scanner(System.in);
        System.out.println(" =========== Enter the length of the RECTANGULAR board =========== ");
        final int length = scanner.nextInt();
        System.out.println(" =========== Enter the breadth of the RECTANGULAR board =========== ");
        final int breadth = scanner.nextInt();
        final Board board = Board.builder()
                .length(length)
                .breadth(breadth)
                .boardType(BoardType.RECTANGULAR)
                .boardLayout(new HashMap<>())
                .build();
        PopulateBoardUtil.populateSnakesAndLadders(board);
        System.out.println(" =========== RECTANGULAR board initialized =========== ");
        return board;
    }

    @Override
    public void printBoard(final Board board) {
        for (int currRow = board.getBreadth(); currRow >= NumberUtils.INTEGER_ONE; currRow--) {
            for (int currCol = board.getLength(); currCol >= NumberUtils.INTEGER_ONE; currCol--) {
                board.getBoardLayout().get(currRow * currCol).print();
            }
            System.out.println();
        }
        System.out.println();
    }
}
