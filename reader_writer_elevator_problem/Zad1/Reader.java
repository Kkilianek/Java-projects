package Zad1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    static boolean przeczytanoWszystkie = false;
    static private int counterWszystkich = 0;
    static private int liczbaWątków = 0;
    protected List<Book> kolejka;
    boolean przeczytanoWszystkieOs;
    private int counter = 0;

    public Reader(List<Book> kolejka) {
        this.kolejka = kolejka;
    }

    private void czytaj(Book book) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String czas = sdf.format(date);
        System.out.println(Thread.currentThread().getName() + " " +
                czas + " " + "czyta książkę " + book);
        counter++;
        counterWszystkich++;
        try {
            Thread.sleep(((ThreadLocalRandom.current().nextInt(1000))));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        liczbaWątków++;
        while (!przeczytanoWszystkieOs) {
            if (kolejka.size() != 0) {
                if (counter < kolejka.size()) {
                    if (kolejka.get(counter) != null) {
                        synchronized (kolejka.get(counter)) {
                            czytaj(kolejka.get(counter));
                        }
                    }
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (counter == Writer.numOfBooks) {
                System.out.println(Thread.currentThread().getName()+" skończył czytanie");
                przeczytanoWszystkieOs = true;
            }
        }
    }

}
