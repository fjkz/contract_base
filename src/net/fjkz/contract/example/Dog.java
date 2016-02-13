package net.fjkz.contract.example;

public class Dog {
    private String name;
    private int power;
    static int MAX_POWER = 10;

    Dog(String name) {
        this.name = name;
        this.power = 5;
    }

    public String toString() {
        return "name: " + name + ", power: " + power;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int eat(int food) {
        if (food < 0) {
            throw new IllegalArgumentException();
        }
        power += food;
        if (power > MAX_POWER) {
            int remain = power - MAX_POWER;
            power = MAX_POWER;
            return remain;
        } else {
            return 0;
        }
    }

    public String bark() {
        power--;
        if (power < 0) {
            power = 0;
            return "";
        } else {
            return "WOOF WOOF";
        }
    }
}
