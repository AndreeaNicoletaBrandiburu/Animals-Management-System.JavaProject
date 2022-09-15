package main.repository;

import main.assertions.AssertAdd;
import main.csv.LineConvertor;
import main.csv.WorkWithCsv;
import main.exception.AnimalDoesNotExistException;
import main.model.animal.*;
import main.utils.AnimalSingleton;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class AnimalQueriesImpl implements AnimalQueries {
    private final static List<Animal> animals = AnimalSingleton.getInstance().getAnimals();
    private final static LineConvertor lineConvertor = new LineConvertor();

    @Override
    public List<Animal> getAllByBehaviour(Behaviour comportament) {
        return animals.stream().filter(animal -> animal.getBehaviour().equals(comportament)).collect(Collectors.toList());
    }

    @Override
    public List<Animal> getByColor(String color) {
        return animals.stream().filter(animal -> animal.getColor().equals(color)).collect(Collectors.toList());
    }

    @Override
    public List<Animal> getByChangingColor(boolean changingColor) {
        return animals.stream().filter(animal -> animal.isChangesColor() == changingColor).collect(Collectors.toList());
    }

    @Override
    public Animal getById(int id) {
        return animals.stream().filter(animal -> animal.getId() == id).findAny().orElseThrow(() -> new AnimalDoesNotExistException("The animal with id " + id + " does not exist in the system. Please, try again."));
    }

    @Override
    public LinkedHashSet<Animal> getBiggerThanByDimension(double dimension) {
        return animals.stream().filter(animal -> animal.getDimension() > dimension).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Animal> getSmallerThanByDimension(double dimension) {
        return animals.stream().filter(animal -> animal.getDimension() < dimension).collect(Collectors.toSet());
    }

    @Override
    public Collection<Animal> getAllRodent() {
        return animals.stream().filter(Animal::isRodent).collect(Collectors.toSet());
    }

    @Override
    public List<Animal> getAllIsSpeaking() {
        return animals.stream().filter(Animal::isSpeaking).collect(Collectors.toList());
    }

    @Override
    public List<Animal> getByName(String name) {
        return animals.stream().filter(animal -> animal.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Animal> getByGender(Gender gender) {
        return animals.stream().filter(animal -> animal.getGen().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animals;
    }

    @Override
    public void loadFromCsv(String fileName) {
        WorkWithCsv workWithCsv = WorkWithCsv.getInstance(fileName);
        int lineNumber = 1;
        String line = null;
        while ((line = workWithCsv.readLineFromFile(lineNumber)) != null) {
            if (fileName.toLowerCase().contains("cat")) {
                Cat cat = lineConvertor.convertLineToCat(line);
                addAnimal(cat);
            }
            if (fileName.toLowerCase().contains("dog")) {
                Dog dog = lineConvertor.convertLineToDog(line);
                addAnimal(dog);
            }
            if (fileName.toLowerCase().contains("parrot")) {
                Parrot parrot = lineConvertor.convertLineToParrot(line);
                addAnimal(parrot);
            }
            lineNumber++;
        }
    }

    @Override
    public void addAnimal(Animal animal) {
        AssertAdd.assertAnimal(animal);
        animals.add(animal);
    }

    @Override
    public void addAnimalToCsv(Animal animal, String fileName) {
        WorkWithCsv workWithCsv = WorkWithCsv.getInstance(fileName);
        String line = lineConvertor.fromAnimalToLine(animal);
        workWithCsv.writeLineInFile(line);
    }

    @Override
    public void removeAnimalById(int id) {
        Animal animal = getById(id);
        animals.remove(animal);
    }
}
