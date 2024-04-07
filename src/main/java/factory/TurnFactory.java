package factory;

import constants.TurnStrategy;
import handler.turn.TurnHandler;
import handler.turn.impl.QueueBasedTurnHandler;
import handler.turn.impl.RandomTurnHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TurnFactory<E> {

    @NonNull QueueBasedTurnHandler queueBasedTurnHandler;
    @NonNull RandomTurnHandler randomTurnHandler;

    public TurnHandler getTurnHandler(final TurnStrategy turnStrategy) {
        switch (turnStrategy) {
            case QUEUE:
                return queueBasedTurnHandler;
            case RANDOM:
                return randomTurnHandler;
            default:
                throw new IllegalStateException(String.format("TurnStrategy : %s has no implementation defined.", turnStrategy));
        }
    }
}
