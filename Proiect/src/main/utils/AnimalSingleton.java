package main.utils;

import main.model.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public final class AnimalSingleton {
    private static final AnimalSingleton instance = new AnimalSingleton();
    private final List<Animal> animals = new ArrayList<>();

    private AnimalSingleton() {
    }

    public static AnimalSingleton getInstance() {
        return instance;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
