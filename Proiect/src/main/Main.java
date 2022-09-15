package main;

import main.model.animal.*;
import main.repository.AnimalQueries;
import main.repository.AnimalQueriesImpl;
import main.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static boolean CSV = false;
    private static final AnimalQueries animalQueries = new AnimalQueriesImpl();

    private final static String CAT_FILE_NAME = "cat.csv";
    private final static String DOG_FILE_NAME = "dog.csv";
    private final static String LOG_FILE_NAME = "log.csv";

    private final static String PARROT_FILE_NAME = "parrot.csv";
    private final static List<String> fileNames = List.of(CAT_FILE_NAME, DOG_FILE_NAME, PARROT_FILE_NAME);

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println("Choose from the following options: CSV, normal");
        String line;
        line = bufferedReader.readLine();
        if (line.equalsIgnoreCase("csv")) {
            CSV = true;
        }
        System.out.println("You have the following options: addAnimal, removeAnimal, getAllAnimals, finish");
        if (CSV) {
            fileNames.forEach(animalQueries::loadFromCsv);
        }
        while (!(line = bufferedReader.readLine()).equals("finish")) {
            switch (line) {
                case "addAnimal":
                    addAnimal(bufferedReader);
                    LogUtils.addLogToFile("addAnimal", LocalDateTime.now(), LOG_FILE_NAME);
                    break;
                case "removeAnimal":
                    System.out.println("Give the id of the animal you want to delete");
                    line = bufferedReader.readLine().trim();
                    animalQueries.removeAnimalById(Integer.parseInt(line));
                    LogUtils.addLogToFile("removeAnimal", LocalDateTime.now(), LOG_FILE_NAME);
                    break;
                case "getAllAnimals":
                    System.out.println(animalQueries.getAllAnimals());
                    LogUtils.addLogToFile("getAllAnimals", LocalDateTime.now(), LOG_FILE_NAME);
                    break;
                case "showCommands":
                    System.out.println("The existing commands are: addAnimal, removeAnimal, getAllAnimals, finish");
                    LogUtils.addLogToFile("showCommands", LocalDateTime.now(), LOG_FILE_NAME);
                    break;
                case "allSpeaking":
                    System.out.println(animalQueries.getAllIsSpeaking());
                    LogUtils.addLogToFile("allSpeaking", LocalDateTime.now(), LOG_FILE_NAME);
                    break;
                case "getAllByColor":
                    System.out.println("Give the color: ");
                    line = bufferedReader.readLine().trim();
                    System.out.println(animalQueries.getByColor(line));
                    LogUtils.addLogToFile("getAllByColor", LocalDateTime.now(), LOG_FILE_NAME);
                    break;
                default:
                    System.err.println("You didn't enter one of the existing commands. Please try again. Write \"showCommands\" if " + "you want to see the existing commands");
            }
        }
    }

    private static void addAnimal(BufferedReader bufferedReader) throws IOException {
        String line;
        Animal animal = null;
        while (animal == null) {
            try {
                System.out.println("Choose one of the following species: cat, chameleon, dog, dove, hamster, parrot, rabbit:");
                line = bufferedReader.readLine().trim();
                switch (line.toLowerCase()) {
                    case "dog":
                        animal = readAttributes(bufferedReader);
                        Dog dog = new Dog(animal);
                        animalQueries.addAnimal(dog);
                        if (CSV) {
                            animalQueries.addAnimalToCsv(dog, DOG_FILE_NAME);
                        }
                        System.out.println("The following dog has been inserted " + dog);
                        break;
                    case "cat":
                        animal = readAttributes(bufferedReader);
                        Cat cat = new Cat(animal);
                        animalQueries.addAnimal(cat);
                        if (CSV) {
                            animalQueries.addAnimalToCsv(cat, CAT_FILE_NAME);
                        }
                        System.out.println("The following cat has been inserted " + cat);
                        break;
                    case "parrot":
                        animal = readAttributes(bufferedReader);
                        Parrot parrot = new Parrot(animal);
                        animalQueries.addAnimal(parrot);
                        if (CSV) {
                            animalQueries.addAnimalToCsv(parrot, PARROT_FILE_NAME);
                        }
                        System.out.println("The following parrot has been inserted " + parrot);
                        break;
                    default:
                        System.err.println("You didn't specify one of the existing species");
                }
            } catch (RuntimeException exception) {
                System.err.println(exception.getMessage());
                animal = null;
            }
        }
    }

    private static Animal readAttributes(BufferedReader bufferedReader) throws IOException {
        String line;
        System.out.println("Add the following attributes separated by spaces " + "id (int), name (String), gender (Gender), behaviour (Behaviour), age (int), dimension (double)," + " color (String), isChangingColor (boolean), isRodent (boolean), isSpeaking (boolean)");
        line = bufferedReader.readLine();
        String[] attributes = line.split("\\s+");
        return new Animal.AnimalBuilder().id(Integer.parseInt(attributes[0])).name(attributes[1]).gen(Gender.getGender(attributes[2])).behaviour(Behaviour.getBehaviour(attributes[3])).age(Integer.parseInt(attributes[4])).dimension(Double.parseDouble(attributes[5])).color(attributes[6]).changesColor(Boolean.parseBoolean(attributes[7])).rodent(Boolean.parseBoolean(attributes[8])).speaking(Boolean.parseBoolean(attributes[9])).build();
    }
}