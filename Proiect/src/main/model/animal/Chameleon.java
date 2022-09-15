package main.model.animal;

public class Chameleon extends Animal {
    public Chameleon(Animal animal) {
        super(animal);
    }

    @Override
    public String toString() {
        return "Chameleon{" +
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
