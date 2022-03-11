package Zad2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Floor extends Thread {
    List<Passenger> passengers = new LinkedList<>();
    int floorNumber;
    double p; //prawdopodobieństwo utworzenia nowego pasażera na piętrze
    static double próg; //próg powyżej, którego generowany jest nowy pasażer
    private int t; //czas generowania nowego pasażera na piętrze
    private int maxCapacity = 10; //zakłdamy, że na piętrze zmieści się tylko określona liczba osób oczekujących na windę
    private int currentCapacity = 0;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public void run() {
        while (true) {
            t = ThreadLocalRandom.current().nextInt(300, 1000);
            p = ThreadLocalRandom.current().nextDouble(0, 1);
            if (p >= próg) {
                if (currentCapacity < maxCapacity) {
                    synchronized (passengers){
                        passengers.add(new Passenger(floorNumber));
                        currentCapacity++;
                    }
                }
            }
            try {
                sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
