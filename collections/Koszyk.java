package collections;

import java.math.BigDecimal;
import java.util.Stack;

public class Koszyk extends Stack<Produkt> {
    private BigDecimal sum = BigDecimal.valueOf(0);

    public BigDecimal getSum() {
        return sum;
    }

    @Override
    public synchronized int size() {
        return super.size();
    }

    @Override
    public boolean empty() {
        if (size() == 0) {
            System.out.println("KOSZYK JEST PUSTY! NIE MOŻNA OBSŁUŻYĆ KLIENTA!");
        }
        return super.empty();
    }

    @Override
    public synchronized Produkt pop() {
        return super.pop();
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }

    @Override
    public synchronized boolean add(Produkt produkt) {
        produkt = new ProduktWKoszyku(produkt.nazwa, produkt.cena,produkt.liczbaSztukProduktu);
        if (produkt.liczbaSztukProduktu < 0) {
            System.out.println("Nie można dodać produktu " + produkt.nazwa + "! Brak ilościowy!");
            return false;
        } else {
            sum = sum.add(BigDecimal.valueOf(produkt.cena));
            System.out.println(produkt.toString()+" ZOSTAŁ DODANY DO KOSZYKA.");
            return super.add(produkt);
        }
    }
}
