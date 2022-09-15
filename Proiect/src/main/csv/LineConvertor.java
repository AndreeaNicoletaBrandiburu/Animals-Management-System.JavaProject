package main.csv;

import main.model.animal.*;

public class LineConvertor {
    public Cat convertLineToCat(String line) {
        return new Cat(getAnimalFromLines(line.split(",")));
    }

    public Dog convertLineToDog(String line) {
        return new Dog(getAnimalFromLines(line.split(",")));
    }

    public Parrot convertLineToParrot(String line) {
        return new Parrot(getAnimalFromLines(line.split(",")));
    }

    private static Animal getAnimalFromLines(String[] lines) {
        return new Animal.AnimalBuilder().id(Integer.parseInt(lines[0])).name(lines[1]).gen(Gender.getGender(lines[2])).behaviour(Behaviour.getBehaviour(lines[3])).age(Integer.parseInt(lines[4])).dimension(Double.parseDouble(lines[5])).color(lines[6]).changesColor(Boolean.parseBoolean(lines[7])).rodent(Boolean.parseBoolean(lines[8])).speaking(Boolean.parseBoolean(lines[9])).build();
    }

    public String fromAnimalToLine(Animal animal) {
        return animal.getId() + "," + animal.getName() + "," + Gender.getNameFromGender(animal.getGen()) + "," + Behaviour.getNameFromBehaviour(animal.getBehaviour()) + "," + +animal.getAge() + "," + animal.getDimension() + "," + animal.getColor() + "," + animal.isChangesColor() + "," + animal.isRodent() + "," + animal.isSpeaking();
    }
}
