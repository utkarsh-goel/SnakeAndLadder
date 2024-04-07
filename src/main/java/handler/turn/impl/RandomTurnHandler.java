package handler.turn.impl;

import handler.turn.TurnHandler;
import model.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class RandomTurnHandler implements TurnHandler {

    @Override
    public Collection<Player> getDataType() {
        return new ArrayList<>();
    }

    @Override
    public Player getNextPlayer(Collection<Player> playerCollection) {
        final int size = playerCollection.size();
        final Random random = new Random();
        return new ArrayList<>(playerCollection).get(random.nextInt(size));
    }
}
