package ru.stqa.pft.sandbox;

public class Equation {
    private double a;
    private double b;
    private double c;

    private int number;

    public Equation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        double d = b * b - 4 * a * c;

        if (a != 0) {
            if (d > 0) {
                number = 2;
            }
            else if (d == 0) {
                number = 1;
            }
            else {
                number = 0;
            }
        }
        else if (b != 0) {
            number = 1;
        }
        else if (c != 0) {
            number = 0;
        }
        else {
            number = -1;
        }
    }

    public int rootNumber() {
        return number;
    }
}
