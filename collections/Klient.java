package collections;

public class Klient {
    static int liczbaKlientów = 0;
    int iD;
    Koszyk koszyk = new Koszyk();
    Klient(){
        iD=liczbaKlientów+1;
        liczbaKlientów++;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "iD=" + iD +
                ", koszyk=" + koszyk +
                '}';
    }
}
