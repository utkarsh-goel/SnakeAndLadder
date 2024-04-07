package factory;

import constants.BoardType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import handler.board.BoardHandler;
import handler.board.impl.RectangularBoardHandler;
import handler.board.impl.SquareBoardHandler;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BoardFactory {

    @NonNull
    SquareBoardHandler squareBoard;

    @NonNull
    RectangularBoardHandler rectangularBoard;

    public BoardHandler getBoard(final BoardType boardType) {
        switch (boardType) {
            case SQUARE:
                return squareBoard;
            case RECTANGULAR:
                return rectangularBoard;
            default:
                throw new IllegalStateException(String.format("BoardType : %s has no implementation defined.", boardType));
        }
    }
}
