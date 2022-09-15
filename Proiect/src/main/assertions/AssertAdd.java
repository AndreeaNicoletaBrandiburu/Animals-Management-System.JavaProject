package main.assertions;

import main.exception.DogHasNotProperDimensionException;
import main.exception.ParrotMustTalkException;
import main.model.animal.Animal;
import main.model.animal.Dog;
import main.model.animal.Parrot;

import static main.utils.Constants.MINIMUM_DIMENSION_DOG_IN_CM;

public class AssertAdd {
    public static void assertAnimal(Animal animal) {
        if (animal instanceof Dog) {
            assertDog((Dog) animal);
        }
        if (animal instanceof Parrot) {
            asserParrot((Parrot) animal);
        }
    }

    private static void assertDog(Dog dog) {
        if (dog.getDimension() < MINIMUM_DIMENSION_DOG_IN_CM) {
            throw new DogHasNotProperDimensionException(dog.getDimension());
        }
    }

    private static void asserParrot(Parrot parrot) {
        if (parrot.isSpeaking()) {
            throw new ParrotMustTalkException();
        }
    }
}
