package handler.turn;

import model.Player;

import java.util.Collection;

public interface TurnHandler {

    Collection<Player> getDataType();

    Player getNextPlayer(final Collection<Player> playerCollection);
}
