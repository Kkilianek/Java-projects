package Zad2;

import java.util.concurrent.ThreadLocalRandom;

public class Passenger {
    static int counter = 0;
    int numer;
    int from;
    int to;


    boolean direction;
    public Passenger(int from) {
        numer=counter;
        setFrom(from);
        to = ThreadLocalRandom.current().nextInt(0, Elevator.F);
        while (from == to) {
            to = ThreadLocalRandom.current().nextInt(0, Elevator.F);
        }
        direction = to > from;
        counter++;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "numer=" + numer +
                ", from=" + from +
                ", to=" + to +
                ", direction=" + direction +
                '}';
    }
}
