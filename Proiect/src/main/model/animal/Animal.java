package main.model.animal;

import java.util.Objects;

public class Animal {
    protected int id;
    protected String name;
    protected Gender gen;
    protected Behaviour behaviour;
    protected int age;
    protected double dimension;
    protected String color;
    protected boolean changesColor;
    protected boolean rodent;
    protected boolean speaking;

    protected Animal(Animal animal) {
        this.id = animal.id;
        this.name = animal.name;
        this.gen = animal.gen;
        this.behaviour = animal.behaviour;
        this.age = animal.age;
        this.dimension = animal.dimension;
        this.color = animal.color;
        this.changesColor = animal.changesColor;
        this.rodent = animal.rodent;
        this.speaking = animal.speaking;
    }

    private Animal(AnimalBuilder animalBuilder) {
        this.id = animalBuilder.id;
        this.name = animalBuilder.name;
        this.gen = animalBuilder.gen;
        this.behaviour = animalBuilder.behaviour;
        this.age = animalBuilder.age;
        this.dimension = animalBuilder.dimension;
        this.color = animalBuilder.color;
        this.changesColor = animalBuilder.changesColor;
        this.rodent = animalBuilder.rodent;
        this.speaking = animalBuilder.speaking;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGen() {
        return gen;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public int getAge() {
        return age;
    }

    public double getDimension() {
        return dimension;
    }

    public String getColor() {
        return color;
    }

    public boolean isChangesColor() {
        return changesColor;
    }

    public boolean isRodent() {
        return rodent;
    }

    public boolean isSpeaking() {
        return speaking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class AnimalBuilder {
        private int id;
        private String name;
        private Gender gen;
        private Behaviour behaviour;
        private int age;
        private double dimension;
        private String color;
        private boolean changesColor;
        private boolean rodent;
        private boolean speaking;

        public AnimalBuilder id(int id) {
            this.id = id;
            return this;
        }

        public AnimalBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AnimalBuilder gen(Gender gen) {
            this.gen = gen;
            return this;
        }

        public AnimalBuilder behaviour(Behaviour behaviour) {
            this.behaviour = behaviour;
            return this;
        }

        public AnimalBuilder age(int age) {
            this.age = age;
            return this;
        }

        public AnimalBuilder dimension(double dimension) {
            this.dimension = dimension;
            return this;
        }

        public AnimalBuilder color(String color) {
            this.color = color;
            return this;
        }

        public AnimalBuilder changesColor(boolean changesColor) {
            this.changesColor = changesColor;
            return this;
        }

        public AnimalBuilder rodent(boolean rodent) {
            this.rodent = rodent;
            return this;
        }

        public AnimalBuilder speaking(boolean speaking) {
            this.speaking = speaking;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }

    }
}
