package main.model.animal;

public class Hamster extends Animal {
    public Hamster(Animal animal) {
        super(animal);
    }

    @Override
    public String toString() {
        return "Hamster{" +
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
