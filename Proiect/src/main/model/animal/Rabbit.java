package main.model.animal;

public class Rabbit extends Animal{
    public Rabbit(Animal animal) {
        super(animal);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
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
