package input_output_concurrency;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Zadanie2 {
    public static void v1() {
        Properties dane = new Properties();
        boolean flag = true;
        File file = new File("properties.txt");
        while (flag) {
            try {
                System.out.print("WITAJ UŻYTKOWNIKU! PODAJ PROSZĘ NAZWĘ PARAMETRU, KTÓREGO BĘDZIESZ UŻYWAĆ: ");
                Scanner scanner = new Scanner(System.in);
                String paramname = scanner.nextLine();
                System.out.print("TERAZ WYBIERZ TYP UŻYWANEGO PARAMETRU. MOŻESZ PODAĆ String,int,double lub boolean: ");
                String paramtype = scanner.nextLine();
                if (paramtype.equalsIgnoreCase("string")) {
                    paramtype = "String";
                } else if (paramtype.equalsIgnoreCase("int")) {
                    paramtype = "int";
                } else if (paramtype.equalsIgnoreCase("double")) {
                    paramtype = "double";
                } else if (paramtype.equalsIgnoreCase("boolean")) {
                    paramtype = "boolean";
                } else {
                    throw new Exception("Nie rozpoznano typu danych. Wybierz typ danych z podanych przez aplikację!");
                }
                System.out.print("A TERAZ PODAJ WARTOŚĆ DLA SWOJEGO PARAMETRU:");
                String paramval = switch (paramtype) {
                    case "String" -> scanner.nextLine();
                    case "int" -> String.valueOf(scanner.nextInt());
                    case "double" -> String.valueOf(scanner.nextDouble());
                    case "boolean" -> String.valueOf(scanner.nextBoolean());
                    default -> null;
                };
                dane.put(paramname, paramval);
                System.out.println("OTO PODANE PRZEZ CIEBIE DANE:");
                System.out.println(dane);
                System.out.println("Czy chcesz podać jeszcze inne wartości? Podaj Y lub N");
                Scanner input = new Scanner(System.in);
                String choice = input.nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    System.out.println("PROGRAM DOKONUJE ZAPISU DO PLIKU I KOŃCZY PRACĘ.");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    dane.store(fileOutputStream, null);
                    fileOutputStream.close();
                    flag = false;
                } else if (choice.equalsIgnoreCase("y")) {
                    continue;
                } else try {
                    throw new Exception("Podano nierozpoznany znak");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void v2() {
        //WARIANT TYLKO DO ODPALENIA POPRZEZ TERMINAL - SYSTEM.CONSOLE W IDEI ZWRACA NULL!
        Properties dane = new Properties();
        boolean flag = true;
        File file = new File("properties.txt");
        while (flag) {
            try {
                System.out.print("WITAJ UŻYTKOWNIKU! PODAJ PROSZĘ NAZWĘ PARAMETRU, KTÓREGO BĘDZIESZ UŻYWAĆ: ");
                Console console = System.console();
                String paramname = console.readLine();
                System.out.print("TERAZ WYBIERZ TYP UŻYWANEGO PARAMETRU. MOŻESZ PODAĆ String,int,double lub boolean: ");
                String paramtype = console.readLine();
                if (paramtype.equalsIgnoreCase("string")) {
                    paramtype = "String";
                } else if (paramtype.equalsIgnoreCase("int")) {
                    paramtype = "int";
                } else if (paramtype.equalsIgnoreCase("double")) {
                    paramtype = "double";
                } else if (paramtype.equalsIgnoreCase("boolean")) {
                    paramtype = "boolean";
                } else {
                    throw new Exception("Nie rozpoznano typu danych. Wybierz typ danych z podanych przez aplikację!");
                }
                System.out.print("A TERAZ PODAJ WARTOŚĆ DLA SWOJEGO PARAMETRU:");
                String paramval = switch (paramtype) {
                    case "String" -> console.readLine();
                    case "int" -> String.valueOf(Integer.parseInt(console.readLine()));
                    case "double" -> String.valueOf(Double.parseDouble(console.readLine()));
                    case "boolean" -> String.valueOf(Boolean.parseBoolean(console.readLine()));
                    default -> null;
                };
                dane.put(paramname, paramval);
                System.out.println("OTO PODANE PRZEZ CIEBIE DANE:");
                System.out.println(dane);
                System.out.println("Czy chcesz podać jeszcze inne wartości? Podaj Y lub N");
                Console input = System.console();
                String choice = input.readLine();
                if (choice.equalsIgnoreCase("n")) {
                    System.out.println("PROGRAM DOKONUJE ZAPISU DO PLIKU I KOŃCZY PRACĘ.");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    dane.store(fileOutputStream, null);
                    fileOutputStream.close();
                    flag = false;
                } else if (choice.equalsIgnoreCase("y")) {
                    continue;
                } else try {
                    throw new Exception("Podano nierozpoznany znak");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void odczyt(){
        Properties odczytane = new Properties();
        try{
            odczytane.load(new FileInputStream("properties.txt"));
            System.out.println("Poprawnie odczytano dane! Oto one:");
            System.out.println(odczytane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        v1();
//        v2();
        odczyt();
    }
}
