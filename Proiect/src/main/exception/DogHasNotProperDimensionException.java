package main.exception;

import static main.utils.Constants.MINIMUM_DIMENSION_DOG_IN_CM;

public class DogHasNotProperDimensionException extends RuntimeException {
    public DogHasNotProperDimensionException(double dimension) {
        super("The dog you inserted has " + dimension + " dimension, but the minimum allowed is " + MINIMUM_DIMENSION_DOG_IN_CM);
    }
}
