package Zad1;

import java.util.ArrayList;
import java.util.List;

public class ReaderWriterproblem {
    public static boolean areAllTrue(boolean[] array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    public static void main(String[] args) {
        int numOfWriters = 10; //W
        int numOfReaders = 5; //R
        Writer.numOfBooks = 20; //B

        List<Book> kolejkaKsiążek = new ArrayList<>();

        for (int i = 0; i < numOfWriters; i++) {
            Thread thread = new Thread(new Writer(kolejkaKsiążek), "Pisarz nr " + (i + 1));
            thread.start();
        }
        Thread[] czytelnicy = new Thread[numOfReaders];
        for (int i = 0; i < numOfReaders; i++) {
            czytelnicy[i] = new Thread(new Reader(kolejkaKsiążek), "Czytelnik nr " + (i + 1));
            czytelnicy[i].start();
        }
        boolean[] przeczytano = new boolean[numOfReaders];

        while (!Reader.przeczytanoWszystkie) {
            for (int i = 0; i < czytelnicy.length; i++) {
                if (czytelnicy[i].isAlive()) {
                    przeczytano[i] = true;
                }
            }
            Reader.przeczytanoWszystkie = areAllTrue(przeczytano);
        }
        if (Writer.napisanoWszystkie) {
            System.exit(0);
        }
    }
}
