package lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Zadanie2_SKLEP {
    public static void main(String[] args) throws IOException {
        BufferedReader readernazw = null;
        BufferedReader readercen = null;
        BufferedReader readerliczb = null;
        try {
            int lines = 0; //liczba linii w plikach tekstowych dotyczących produktów
            File pliknazwa = new File("Lab2\\src\\lab2\\produkty.txt");
            File plikcena = new File("Lab2\\src\\lab2\\produkty_cena.txt");
            File plikliczb = new File("Lab2\\src\\lab2\\produkty_liczby.txt");
            BufferedReader temp = new BufferedReader(new FileReader(pliknazwa));
            readercen = new BufferedReader(new FileReader(plikcena));
            ArrayList<Produkt> listaProduktów = new ArrayList<>();
            ArrayList<Klient> listaKlientów = new ArrayList<>();
            LinkedList<Klient> kolejka = new LinkedList<>();
            //Dodawanie przykładowych produktów
            while (temp.readLine() != null) {
                lines++;
            }
            temp.close();
            readernazw = new BufferedReader(new FileReader(pliknazwa));
            readerliczb = new BufferedReader(new FileReader(plikliczb));
            String[] nazwy = new String[lines];
            double[] ceny = new double[lines];
            int[] liczby = new int[lines];
            for (int i = 0; i < lines; i++) {
                nazwy[i] = readernazw.readLine();
//                System.out.println(nazwy[i]);
            }
            for (int i = 0; i < lines; i++) {
                ceny[i] = Double.valueOf(readercen.readLine());
//                System.out.println(ceny[i]);
            }
            for (int i = 0; i < lines; i++) {
                liczby[i] = Integer.valueOf(readerliczb.readLine());
//                System.out.println(liczby[i]);
            }
            for (int i = 0; i < ceny.length; i++) {
                listaProduktów.add(new Produkt(nazwy[i], ceny[i],liczby[i]));
//                System.out.println(listaProduktów.get(i));
            }
            //Dodawanie przykładowych klientów do sklepu
            for (int i = 0; i < 15; i++) {
                listaKlientów.add(new Klient());
            }
            // Klienci wybierają produkty
            for (Klient klient : listaKlientów) {
                for (int i = 0; i < listaProduktów.size(); i++) {
                    int liczebnosc = listaProduktów.size() - 1 - ((int) (Math.random() * 16));
                    klient.koszyk.add(listaProduktów.get(liczebnosc));
                    listaProduktów.get(liczebnosc).liczbaSztukProduktu--;
                }
            }
            //Dodawanie klientów do kolejki + Test różności ID klientów + dodanie tylko klientów, którzy mają niepusty koszyk
            for (Klient klient : listaKlientów) {
                if (!klient.koszyk.empty()) {
                    kolejka.add(klient);
                }
            }
            for (Klient klient : kolejka) {
                System.out.println(klient.toString());
            }
            //Obsługiwanie klientów
            System.out.println("OBSŁUGIWANIE KLIENTÓW");
            System.out.println("----------------------------");
            for (Klient klient : kolejka) {
                System.out.println("KLIENT NR " + klient.iD + " MA DO ZAPŁATY " + klient.koszyk.getSum());
            }
            System.out.println("--------------------------------------------------");
            System.out.println("DZIĘKUJEMY ZA SKORZYSTANIE Z USŁUG NASZEGO SKLEPU!");

        } finally {
            if (readernazw != null) {
                readernazw.close();
            }
            if (readercen != null) {
                readercen.close();

            }
            if (readerliczb != null) {
                readerliczb.close();
            }
        }

    }
}
