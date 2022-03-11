package lab2;

public class ProduktWKoszyku extends Produkt {

    ProduktWKoszyku(String name, double price) {
        super(name, price);
        super.liczbaSztukProduktu--;
    }


    public ProduktWKoszyku(String nazwa, double cena, int liczbaSztukProduktu) {
        super(nazwa, cena,liczbaSztukProduktu);
//        super.liczbaSztukProduktu--;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getLiczbaSztukProduktu() {
        return liczbaSztukProduktu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProduktWKoszyku that)) return false;
        if (!super.equals(o)) return false;
        return getLiczbaSztukProduktu() == that.getLiczbaSztukProduktu();
    }
}
