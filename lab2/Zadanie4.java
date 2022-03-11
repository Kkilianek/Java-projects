package lab2;

import java.util.*;

public class Zadanie4 {
    public static void main(String[] args) {
        boolean stoobig = false;
        System.out.println("PROGRAM LOSUJĄCY LICZBY");
        System.out.println("-------------------------");
        Scanner input = new Scanner(System.in);
        System.out.println("JAKĄ MAKSYMALNĄ WARTOŚĆ CHCESZ USTALIĆ?");
        int MAX = input.nextInt();
        System.out.println("ILE LICZB CHCESZ WYLOSOWAĆ?");
        int N = input.nextInt();
        System.out.println("JAKA LICZBA MA PRZERWAĆ LOSOWANIE?");
        int S = input.nextInt();
        if (S > MAX) {
            stoobig = true;
            while (stoobig) {
                System.out.println("PODANO ZA DUŻĄ WARTOŚĆ GRANICZNĄ.");
                System.out.println("PODAJ WŁAŚCIWĄ WARTOŚĆ (S<MAX)!");
                S = input.nextInt();
                if (S < MAX) {
                    System.out.println("UDAŁO SIĘ PODAĆ POPRAWNĄ WARTOŚĆ");
                    stoobig=false;
                }
                else {
                    System.out.println("ZNOWU PODANO NIEPOPRAWNĄ WARTOŚĆ.");
                }
            }
        }
        theDraw(MAX, N, S);
    }

    private static void theDraw(int max, int n, int s) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int number = random.nextInt(max);
            if (number == s) {
                System.out.println("NAPOTKANO NA LICZBĘ ZATRZYMUJĄCĄ PĘTLĘ.");
                System.out.println("KONIEC LOSOWANIA");
                break;
            } else {
                list.add(number);
            }
        }
        // WYPISYWANIE LICZB OD POCZĄTKU LOSOWANIA
        System.out.println("WYLOSOWANO " + list.size() + " LICZB");
        System.out.println("LICZBY WYLOSOWANE W KOLEJNOŚCI LOSOWANIA:");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        // WYPISYWANIE LICZB OD KOŃCA LOSOWANIA
        System.out.println("LICZBY WYLOSOWANE W KOLEJNOŚCI OD KOŃCA");
        List<Integer> temp = new ArrayList<>(list);
        Collections.reverse(temp);
        for (Integer integer : temp) {
            System.out.println(integer);
        }
        // WYPISYWANIE POSORTOWANYCH LICZB
        Set<Integer> sorted = new TreeSet<>(list);
        System.out.println("POSORTOWANE WSZYSTKIE LICZBY: " + sorted);
        System.out.println("ODRZUCONO "+(list.size()-sorted.size())+" LICZB ZE WZGLĘDU NA POWTÓRZENIA");

    }
}
