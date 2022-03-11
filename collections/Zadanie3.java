package collections;

import java.util.*;

public class Zadanie3 {
    //NAJLEPSZĄ IMPLEMENTACJĄ BĘDZIE W TYM WYPADKU TREESET, PONIEWAŻ DOSTANIEMY LICZBY W POSORTOWANEJ KOLEJNOŚCI ZGODNIE Z NATURALNYM PORZĄDKIEM
    public static void main(String[] args) throws RuntimeException {
        try {
            Set<Integer> zbior1 = new TreeSet<>();
            Set<Integer> zbior2 = new TreeSet<>();
            Scanner input = new Scanner(System.in);
            System.out.println("PROGRAM WYKONYWUJĄCY OPERACJE NA DWÓCH ZBIORACH");
            System.out.println("------------------------------------------------");
            System.out.println("WPISZ PROSZĘ LICZBY ZNAJDUJĄCE SIĘ W PIERWSZYM ZBIORZE:");
            boolean want = true;
            UI(zbior1, want);
            want = true;
            System.out.println("WPISZ LICZBY DLA DRUGIEGO ZBIORU:");
            UI(zbior2, want);
            System.out.println("ZBIÓR 1: " + zbior1);
            System.out.println("ZBIÓR 2: " + zbior2);
            System.out.println("WYNIK DODAWANIA: " + suma(zbior1, zbior2));
            System.out.println("WYNIK CZĘŚCI WSPÓLNEJ: " + common(zbior1, zbior2));
            System.out.println("WYNIK ODEJMOWANIA: " + subtract(zbior1, zbior2));

        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

    private static void UI(Set<Integer> set, boolean want) {
        while (want) {
            Scanner IntPUT = new Scanner(System.in);
            Scanner ans = new Scanner(System.in);
            System.out.println("WPISZ LICZBĘ: ");
            Integer temp = IntPUT.nextInt();
            set.add(temp);
            System.out.println("UDAŁO SIĘ DODAĆ LICZBĘ.");
            System.out.println("CZY CHCESZ DODAĆ NASTĘPNĄ LICZBĘ? (Y/N)");
            String answer = ans.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.println("WYBRAŁEŚ Y. DODAJ KOLEJNĄ LICZBĘ");
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("WYBRAŁEŚ N. PRZEJDŹ DALEJ.");
                want = false;
            } else {
                throw new RuntimeException("NIEDOZWOLONY ZNAK");
            }
        }
    }

    private static Set suma(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> finalny = new TreeSet<>(set1);
        List<Integer> list1 = new ArrayList<>(set1);
        List<Integer> list2 = new ArrayList<>(set2);
        for (int i = 0; i < set2.size(); i++) {
            if (!list1.contains(list2.get(i))) {
                finalny.add(list2.get(i));
            }
        }
        return finalny;
    }

    private static Set subtract(Set<Integer> set1, Set<Integer> set2) {
        if(set1.isEmpty()){
            throw new RuntimeException("PUSTY ZBIÓR, OD KTÓREGO CHCIANO ODJĄĆ");
        }
        Set<Integer> finalny = new TreeSet<>(set1);
        List<Integer> list1 = new ArrayList<>(set1);
        List<Integer> list2 = new ArrayList<>(set2);
        for (int i = 0; i < set2.size(); i++) {
            if (list1.contains(list2.get(i))) {
                finalny.remove(list2.get(i));
            }
        }
        return finalny;
    }

    private static Set common(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> finalny = new TreeSet<>(set1);
        finalny.retainAll(set2);
        return finalny;
    }
}
