package handler.turn.impl;

import handler.turn.TurnHandler;
import model.Player;

import java.util.Collection;
import java.util.LinkedList;

public class QueueBasedTurnHandler implements TurnHandler {

    @Override
    public Collection<Player> getDataType() {
        return new LinkedList<>();
    }

    @Override
    public Player getNextPlayer(Collection<Player> playerCollection) {
        return new LinkedList<>(playerCollection).poll();
    }
}
