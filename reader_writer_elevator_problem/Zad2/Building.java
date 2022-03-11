package Zad2;

public class Building implements Runnable {
    Elevator elevator = new Elevator();



    public static void main(String[] args) {
        Elevator.F = 10; //tutaj ustal F - liczbę pięter (łącznie z parterem-czyli piętrem 0)
        Elevator.t = 500; //tutaj podaj czas t przemieszczenia o jedno piętro [ms]
        Floor.próg = 0.3; //tutaj ustal próg prawdopodobieństwa, powyżej którego zostanie wygenerowany nowy pasażer na piętrze
        Thread symulacja = new Thread(new Building());
        symulacja.start();
    }

    @Override
    public void run() {
        Thread elevatorThread = new Thread(elevator);
        elevatorThread.start();
    }
}
