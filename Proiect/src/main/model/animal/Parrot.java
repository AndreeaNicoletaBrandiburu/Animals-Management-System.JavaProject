package main.model.animal;

public class Parrot extends Animal {
    public Parrot(Animal animal) {
        super(animal);
    }

    @Override
    public String toString() {
        return "Parrot{" +
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
