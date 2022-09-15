package main.model.animal;

public class Dog extends Animal {
    public Dog(Animal animal) {
        super(animal);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gen=" + gen +
                ", behaviour=" + behaviour +
                ", age=" + age +
                ", dimension=" + dimension +
                ", color='" + color + '\'' +
                ", changesColor=" + changesColor +
                ", rodent=" + rodent +
                ", speaking=" + speaking +
                '}';
    }
}
