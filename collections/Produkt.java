package lab2;

public class Produkt {
    String nazwa;
    double cena;
    int liczbaSztukProduktu;

    public double getCena() {
        return cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    Produkt(String name, double price) {
        nazwa = name;
        cena = price;
    }
    Produkt(String name, double price, int l) {
        nazwa = name;
        cena = price;
        liczbaSztukProduktu = l;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", liczbaSztukProduktu=" + liczbaSztukProduktu +
                '}';
    }
}
