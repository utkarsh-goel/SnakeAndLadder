import constants.BoardType;
import constants.DiceType;
import constants.TurnStrategy;
import driver.GameDriver;
import exception.GameValidationFailedException;
import exception.InvalidOccupantException;
import factory.BoardFactory;
import factory.DiceFactory;
import factory.TurnFactory;
import handler.turn.impl.QueueBasedTurnHandler;
import handler.turn.impl.RandomTurnHandler;
import handler.board.impl.RectangularBoardHandler;
import handler.board.impl.SquareBoardHandler;
import model.Player;
import handler.dice.impl.CrookedDice;
import handler.dice.impl.NormalDice;

public class Main {

    public static void main(String[] args) throws InvalidOccupantException, GameValidationFailedException {
        final GameDriver gameDriver = new GameDriver(getBoardFactory(), getTurnFactory(), getDiceFactory());
        gameDriver.initGame(BoardType.RECTANGULAR, TurnStrategy.RANDOM, DiceType.NORMAL);
        gameDriver.addPlayer("Superman");
        gameDriver.addPlayer("Batman");
        gameDriver.addPlayer("Joker");
        gameDriver.launchGame();
    }

    private static BoardFactory getBoardFactory() {
        final SquareBoardHandler squareBoardHandler = new SquareBoardHandler();
        final RectangularBoardHandler rectangularBoardHandler = new RectangularBoardHandler();
        return new BoardFactory(squareBoardHandler, rectangularBoardHandler);
    }

    private static DiceFactory getDiceFactory() {
        final CrookedDice crookedDice = new CrookedDice();
        final NormalDice normalDice = new NormalDice();
        return new DiceFactory(normalDice, crookedDice);
    }

    private static TurnFactory<Player> getTurnFactory() {
        final QueueBasedTurnHandler queueBasedTurnHandler = new QueueBasedTurnHandler();
        final RandomTurnHandler randomTurnHandler = new RandomTurnHandler();
        return new TurnFactory<>(queueBasedTurnHandler, randomTurnHandler);
    }

}
