package Zad1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Writer implements Runnable {
    static int numOfBooks = 5; //B
    static boolean napisanoWszystkie = false;
    protected List<Book> kolejka;
    static private int counter;

    public Writer(List<Book> kolejka) {
        this.kolejka = kolejka;
    }

    private void setCounter() {
        synchronized (this) {
            counter++;
        }
    }

    private synchronized int getCounter() {
        return counter;
    }

    private Book pisz() {
        setCounter();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String czas = sdf.format(date);
        Book book = new Book();
        System.out.println(Thread.currentThread().getName() + " " +
                czas + " " + "pisze książkę ");
        try {
            Thread.sleep(((ThreadLocalRandom.current().nextInt(1000))));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void run() {
        while (counter < numOfBooks) {
            Book ksiazka = pisz();
            kolejka.add(ksiazka);
            synchronized (kolejka.get(kolejka.indexOf(ksiazka))){
                kolejka.get(kolejka.indexOf(ksiazka)).setNumer(kolejka.indexOf(ksiazka)+1);
            }
            System.out.println("Dodano książkę "+ksiazka+ " do kolekcji."+" Aktualna liczba książek " + kolejka.size());
        }
        napisanoWszystkie = true;
    }
}
