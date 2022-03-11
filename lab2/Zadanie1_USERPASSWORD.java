package lab2;

import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Zadanie1_USERPASSWORD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> creds = new Hashtable<>();
        int wybor;
        System.out.println("PROGRAM USERPASSWORD");
        System.out.println("------------------------------");
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Dodać nowe dane do bazy");
        System.out.println("2. Spojrzeć na dodane już dane");
        System.out.println("3. Zamknąć program");
        wybor = sc.nextInt();
        try {
            switch (wybor) {
                case 1:
                    credentialInsertion(creds);
                case 2:
                    checkingInserted(creds);
                case 3:
                    System.out.println("ZAMYKANIE PROGRAMU!");
                    System.exit(0);

            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

    private static void checkingInserted(Map<String, String> creds) throws RuntimeException {
        System.out.println("TRYB WYŚWIETLANIA WCZEŚNIEJ DODANYCH DANYCH");
        System.out.println("-------------------------------------------");
        if (creds.isEmpty()) {
            throw new RuntimeException("BAZA DANYCH JEST PUSTA! URUCHOM PROGRAM PONOWNIE I DODAJ DANE!");
        } else {
            System.out.println("Podaj nazwę użytkownika, którą chcesz sprawdzić: ");
            Scanner in = new Scanner(System.in);
            String user = in.nextLine();
            if (creds.containsKey(user)) {
                System.out.print("TWOJE HASŁO TO: ");
                System.out.println(creds.get(user));
            } else {
                System.out.println("Nie znaleziono podanego użytkownika");
            }

        }
    }

    private static void credentialInsertion(Map<String, String> creds) throws RuntimeException {
        boolean go = true;
        boolean qflag = false;
        Scanner input = new Scanner(System.in);
        while (!qflag && go) {
            System.out.println("Podaj nazwę użytkownika: ");
            String user = input.nextLine();
            if (user.equalsIgnoreCase("q")) {
                System.out.println("Podano niedozwolony znak WYJŚCIA Z PROGRAMU.");
                System.out.println("PROGRAM ZAKOŃCZYŁ PRACĘ.");
                qflag = true;
                break;
            } else {
                System.out.println("Podaj hasło: ");
                String password = input.nextLine();
                creds.put(user, password);
                System.out.println("Dane zostały podane poprawnie. Czy chcesz podać kolejne dane?(Y/N)");
                String choose = input.nextLine();
                if (choose.equalsIgnoreCase("y")) {
                    System.out.println("Wybrałeś Y. Przejdź do dodania kolejnych danych.");
                } else if (choose.equalsIgnoreCase("n")) {
                    System.out.println("PROGRAM PRZECHODZI DO NASTĘPNEGO TRYBU PRACY!");
                    go = false;
                } else {
                    throw new RuntimeException("Niedozwolony znak");
                }
            }
        }
    }
}
