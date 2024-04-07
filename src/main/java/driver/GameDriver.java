package driver;

import constants.BoardType;
import constants.TurnStrategy;
import exception.GameValidationFailedException;
import exception.InvalidOccupantException;
import factory.BoardFactory;
import factory.TurnFactory;
import handler.turn.TurnHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import model.Board;
import org.apache.commons.lang3.math.NumberUtils;

import handler.board.BoardHandler;
import model.Player;
import model.cellOccupant.CellOccupant;


import handler.dice.Dice;
import factory.DiceFactory;
import constants.DiceType;
import utils.MoveValidator;

import java.util.*;

/**
 * Driver class to initialise and launch game
 */
@RequiredArgsConstructor
public class GameDriver {

    @NonNull
    BoardFactory boardFactory;

    @NonNull
    TurnFactory<Player> turnFactory;

    @NonNull
    DiceFactory diceFactory;

    Board board;
    Collection<Player> players;
    int size;
    Dice dice;
    TurnHandler turnHandler;
    BoardHandler boardHandler;

    /**
     * Initialises all the variables associated with game
     * @param boardType : Board Type
     * @param turnStrategy : Turn Strategy
     * @param diceType : Type of Dice
     */
    public void initGame(@NonNull final BoardType boardType,
                         @NonNull final TurnStrategy turnStrategy,
                         @NonNull final DiceType diceType) throws InvalidOccupantException {
        boardHandler = boardFactory.getBoard(boardType);
        board = boardHandler.initializeBoard();

        size = board.getLength() * board.getBreadth();

        turnHandler = turnFactory.getTurnHandler(turnStrategy);
        players = turnHandler.getDataType();

        dice = diceFactory.getDice(diceType);
        boardHandler.printBoard(board);
    }

    /**
     * Launches Game. Player play their turn sequentially.
     */
    public void launchGame() throws GameValidationFailedException {
        validateGameStart();
        System.out.println();
        System.out.println("************************* Game Started *************************");
        while (players.size() > NumberUtils.INTEGER_ONE) {
            final Player currentPlayer = turnHandler.getNextPlayer(players);
            System.out.printf("%n******** %s's turn. Current Cell %d. Rolling Dice.********%n", currentPlayer.getPlayerName(), currentPlayer.getPosition());
            makeMove(currentPlayer);
            if (currentPlayer.getPosition() == size) {
                System.out.printf("%n%n******** Congratulations %s, You Won !! ******** %n%n", currentPlayer.getPlayerName());
                players = new LinkedList<>();
            } else {
                players.add(currentPlayer);
            }
        }
    }

    /**
     * Adds player to the game.
     * @param playerName : Name of the player
     */
    public void addPlayer(@NonNull final String playerName) {
        players.add(new Player(NumberUtils.INTEGER_ZERO, playerName));
    }

    private void validateGameStart() throws GameValidationFailedException {
        if (players.size() < NumberUtils.INTEGER_ONE) {
            throw new GameValidationFailedException("Game cannot be started without any player");
        }
    }

    private void makeMove(final Player currentPlayer) {
        int currenPosition = currentPlayer.getPosition();
        int numberRolled = dice.roll();
        System.out.printf("%s got : %d%n", currentPlayer.getPlayerName(), numberRolled);
        int finalPosition = currenPosition + numberRolled;
        if (MoveValidator.isValidMove(finalPosition, size)) {
            final CellOccupant cellOccupant = board.getBoardLayout().get(finalPosition);
            if (cellOccupant.getEncounterMessage(finalPosition).isPresent()) {
                System.out.println(cellOccupant.getEncounterMessage(finalPosition).get());
            }
            finalPosition = cellOccupant.getEndPosition();
            System.out.printf("Taking %s to: %d%n", currentPlayer.getPlayerName(), finalPosition);
        } else {
            System.out.printf("Sorry. You cannot jump by %d places when you are at %d. Try again in next turn !%n", numberRolled, currenPosition);
            finalPosition = currenPosition;
        }
        currentPlayer.setPosition(finalPosition);
    }

}
