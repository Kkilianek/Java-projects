package Zad2;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Elevator implements Runnable {
    static int F;
    List<Passenger> passengers = new LinkedList<>();
    List<Passenger> toRemove = new LinkedList<>();
    List<Passenger> toRemovefromFloor = new LinkedList<>();
    boolean direction = true; //w górę to true, w dół to false (startuje od jazdy w górę z parteru)
    int currentFloor = 0; //startuje od zera
    int maxCapacity = 10; //maksymalna liczba pasażerów, którą winda może zmieścić
    int currCapacity = 0; //obecna ilość pasażerów w windzie
    static int t; //czas przemieszczenia o jedno piętro [ms]
    Floor[] floors = new Floor[F];

    {
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new Floor(i);
            floors[i].start();
        }
    }

    private boolean nobodyWaiting() {
        int counter = 0;
        for (Floor value : floors) {
            if (!value.passengers.isEmpty()) {
                counter++;
            }
        }
        return counter == 0;
    }

    @Override
    public void run() {
        while (true) {
            if (!nobodyWaiting()) {
                //jeśli lista pasażerów w windzie nie jest pusta, sprawdzamy najpierw, czy jacyś pasażerowie mogą wysiąść
                if (!passengers.isEmpty()) {
                    for (Passenger passenger : passengers) {
                        if (passenger.to == currentFloor) {
                            toRemove.add(passenger);
                            System.out.println("Pasażer " + passenger + " opuszcza windę");
                            currCapacity--;
                        }
                    }
                }
                passengers.removeAll(toRemove);

                if (currentFloor == (F - 1)) {
                    direction = false;
                }
                synchronized (floors[currentFloor].passengers) {
                    if (currCapacity < maxCapacity) {   //jeśli nie ma pasażerów w windzie, to próbujemy jakichś załadować
                        if (!floors[currentFloor].passengers.isEmpty()) {
                            System.out.println("Winda rozpoczyna obsługę pasażerów na piętrze numer "+currentFloor);
                            System.out.println("Na piętrze znajduje się "+floors[currentFloor].passengers.size()+" pasażerów.");
                            System.out.println("Oto oni:");
                            System.out.println(floors[currentFloor].passengers);
                            for (Passenger passenger : floors[currentFloor].passengers) {
                                if (passenger.direction == direction) { //czy ktoś chce pojechać w tym samym kierunku co winda
                                    if (currCapacity + 1 > maxCapacity) {
                                        System.out.println("Winda osiągnęła maksymalną pojemność.");
                                        break;
                                    } else {
                                        passengers.add(passenger); //dodawanie odpowiedniego pasażera i usuwanie go z kolejki na piętrze
                                        System.out.println("Pasażer " + passengers.get(currCapacity) + " wsiada do windy");
                                        toRemovefromFloor.add(passenger);
                                        currCapacity++;
                                    }
                                }
                            }
                            if (toRemovefromFloor.isEmpty()){
                                System.out.println("Nie znaleziono pasażera, który mógłby teraz wsiąść.");
                            }
                            floors[currentFloor].passengers.removeAll(toRemovefromFloor);
                            toRemovefromFloor.clear();
                        }
                    } else {
                        System.out.println("Winda jest pełna. Nie można dodać więcej pasażerów.");
                    }
                }

				/*
				Zmiana kierunku, gdy winda znajduje się na parterze lub na ostatnim piętrze
				*/
                if (currentFloor == 0) {
                    direction = true;
                }

                if (direction) {
                    System.out.println("Winda jedzie w górę.");
                    System.out.println("Aktualna liczba ludzi w windzie: "+currCapacity);
                    try {
                        sleep(t); //winda podróżuje i odczekuje zadany czas
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentFloor++;
                    System.out.println("Winda znajduje się na piętrze " + currentFloor);
                } else {
                    System.out.println("Winda jedzie w dół.");
                    System.out.println("Aktualna liczba ludzi w windzie: "+currCapacity);
                    try {
                        sleep(t); //winda podróżuje i odczekuje zadany czas
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentFloor--;
                    System.out.println("Winda znajduje się na piętrze " + currentFloor);
                }

            } else {
                //Testowo
                System.out.println("Winda bezczynna na piętrze " + currentFloor);
                System.out.println("Na piętrze znajduje się "+floors[currentFloor].passengers.size()+" pasażerów.");
            }

        }
    }
}

