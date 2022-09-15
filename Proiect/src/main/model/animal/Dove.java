package main.model.animal;

public class Dove extends Animal {
    public Dove(Animal animal) {
        super(animal);
    }

    @Override
    public String toString() {
        return "Dove{" +
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
