package main.repository;

import main.assertions.AssertAdd;
import main.csv.LineConvertor;
import main.csv.WorkWithCsv;
import main.exception.AnimalDoesNotExistException;
import main.model.animal.*;
import main.utils.AnimalSingleton;

import java.sql.*;
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

    @Override
    public void addAnimalToDatabase(Animal animal) {
        String INSERT_DOG_SQL = "INSERT INTO dog VALUES(null,?,?,?,?,?,?,?,?,?)";
        String INSERT_CAT_SQL = "INSERT INTO cat VALUES(null,?,?,?,?,?,?,?,?,?)";
        String INSERT_PARROT_SQL = "INSERT INTO parrot VALUES(null,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/proiect", "root", "")) {
            if (animal instanceof Dog) {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOG_SQL);
                setPreparedStatement(preparedStatement, animal);
                preparedStatement.executeUpdate();
            }
            if (animal instanceof Cat) {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAT_SQL);
                setPreparedStatement(preparedStatement, animal);
                preparedStatement.executeUpdate();
            }
            if (animal instanceof Parrot) {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PARROT_SQL);
                setPreparedStatement(preparedStatement, animal);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("There was an error connecting to the database");
        }
    }

    @Override
    public void loadAnimalsFromDatabase(String table) {
        String SELECT_DOGS_FROM_DATABASE = "SELECT * FROM "  + table;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/proiect", "root", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOGS_FROM_DATABASE);
            ResultSet rs = preparedStatement.executeQuery();
            if(table.toLowerCase().contains("dog")) {
                while (rs.next()) {
                    Dog dogFromDb = new Dog(
                            createAnimalFromResultSet(rs)
                    );
                    addAnimal(dogFromDb);
                }
            }
            if(table.toLowerCase().contains("cat"))  {
                while (rs.next()) {
                    Cat catFromDb = new Cat(
                            createAnimalFromResultSet(rs)
                    );
                    addAnimal(catFromDb);
                }
            }
            if(table.toLowerCase().contains("parrot")){
                while (rs.next()) {
                    Parrot parrotFromDb = new Parrot(
                            createAnimalFromResultSet(rs)
                    );
                    addAnimal(parrotFromDb);
                }
            }
        } catch (SQLException e) {
            System.err.println("There was an error trying to connect to database");
        }
    }

    private static Animal createAnimalFromResultSet(ResultSet rs) throws SQLException {
        return new Animal.AnimalBuilder()
                .id(rs.getInt(1))
                .name(rs.getString(2))
                .gen(Gender.getGender(rs.getString(3)))
                .behaviour(Behaviour.getBehaviour(rs.getString(4)))
                .age(rs.getInt(5))
                .dimension(rs.getDouble(6))
                .color(rs.getString(7))
                .changesColor(rs.getBoolean(8))
                .rodent(rs.getBoolean(9))
                .speaking(rs.getBoolean(10))
                .build();
    }

    private void setPreparedStatement(PreparedStatement preparedStatement, Animal animal) throws SQLException {
        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, Gender.getNameFromGender(animal.getGen()));
        preparedStatement.setString(3, Behaviour.getNameFromBehaviour(animal.getBehaviour()));
        preparedStatement.setInt(4, animal.getAge());
        preparedStatement.setDouble(5, animal.getDimension());
        preparedStatement.setString(6, animal.getColor());
        preparedStatement.setBoolean(7, animal.isChangesColor());
        preparedStatement.setBoolean(8, animal.isRodent());
        preparedStatement.setBoolean(9, animal.isSpeaking());
    }
}
