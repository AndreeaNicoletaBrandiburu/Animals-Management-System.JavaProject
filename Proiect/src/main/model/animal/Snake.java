package main.model.animal;

public class Snake extends Animal{
    public Snake(Animal animal) {
        super(animal);
    }

    @Override
    public String toString() {
        return "Snake{" +
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
