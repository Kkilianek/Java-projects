package Zad1;

public class Book {
    private int numer;

    @Override
    public String toString() {
        return "Book{" +
                "numer=" + numer +
                '}';
    }

    public Book(int numer) {
        this.numer = numer;
    }

    public synchronized void setNumer(int numer) {
        this.numer = numer;
    }

    public synchronized int getNumer() {
        return numer;
    }

    public Book() {
    }
}
