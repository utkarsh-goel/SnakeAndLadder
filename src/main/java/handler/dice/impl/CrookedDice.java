package handler.dice.impl;

import handler.dice.Dice;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Random;

/**
 * Crooked Dice implementation
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CrookedDice implements Dice {

    @Override
    public int roll() {
        final Random random = new Random();
        return 2 + random.nextInt(3) * 2;
    }
}
