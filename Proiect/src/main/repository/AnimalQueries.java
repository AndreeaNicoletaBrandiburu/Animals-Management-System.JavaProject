package main.repository;

import main.model.animal.Animal;
import main.model.animal.Behaviour;
import main.model.animal.Gender;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AnimalQueries {
    List<Animal> getAllByBehaviour(Behaviour comportament);

    List<Animal> getByColor(String culoare);

    List<Animal> getByChangingColor(boolean schimbaCuloarea);

    Animal getById(int id);

    Set<Animal> getBiggerThanByDimension(double dimensiune);

    Set<Animal> getSmallerThanByDimension(double dimensiune);

    Collection<Animal> getAllRodent();

    List<Animal> getAllIsSpeaking();

    List<Animal> getByName(String name);

    List<Animal> getByGender(Gender gen);

    List<Animal> getAllAnimals();

    void loadFromCsv(String fileName);

    void addAnimal(Animal animal);

    void addAnimalToCsv(Animal animal, String fileName) ;

    void removeAnimalById(int id);
}
