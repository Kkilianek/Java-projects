/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quadratic_equation_calculator;

import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author kacpe
 */
public class PD2 {
    int a,b,c;
    Scanner scan = new Scanner(System.in);
    public int[] readData(){
        System.out.println("WITAJ W MOIM PROGRAMIE! PROSZĘ PODAJ PARAMETR a RÓWNANIA KWADRATOWEGO:");
        a=scan.nextInt();
        switch(a){
            case 0:
                    System.out.println("Podano nieprawidłową wartość. Niemożliwe stworzenie równania kwadratowego!");
                    System.out.println("Podaj proszę prawidłową wartość!");
                    a=scan.nextInt();
                    break;
            default:
                System.out.println("Wprowadziłeś prawidłową wartość. Twoje a wynosi: "+ a);
                System.out.println("Podaj teraz wartość dla b:");
                b=scan.nextInt();
                System.out.println("Wprowadziłeś b. Twoje b wynosi: "+ b);
                System.out.println("Podaj teraz wartość dla c:");
                c=scan.nextInt();
                System.out.println("Wprowadziłeś c. Twoje c wynosi: "+ c);
                break;
                
        }
        int[] wspolczynniki= new int[3];
        wspolczynniki[0]=a;
        wspolczynniki[1]=b;
        wspolczynniki[2]=c;
        return wspolczynniki;
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public double[] processData(int a,int b, int c){
        double delta=b*b-4*a*c;
        if (delta<0){
            System.out.println("Delta ujemna, nie można znaleźć rzeczywistych pierwiastków równania kwadratowego");
            System.out.println("KONIEC DZIAŁANIA PROGRAMU!");
            System.out.println("---------------------------------");
            System.exit(0);}
        double[] x= new double[2];
        x[0]=(-b-Math.sqrt(delta))/(2*a);
        x[1]=(-b+Math.sqrt(delta))/(2*a);
        return x;
    }
    
    public void showResult(int a, int b, int c, double x1, double x2){
        System.out.println("WYKONYWANIE OBLICZEŃ...");
        System.out.println("TWOJE RÓWNANIE KWADRATOWE: "+ a + "x^2+"+b+"x+"+c);
        System.out.println("PIERWIASTKI TWOJEGO RÓWNANIA TO: x1="+x1+" x2="+x2);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PD2 pracka = new PD2();
        int[] ws = new int[3];
        double[] x=new double[2];
        ws=pracka.readData();
        x=pracka.processData(ws[0],ws[1],ws[2]);
        pracka.showResult(ws[0],ws[1],ws[2],x[0],x[1]);
        
        
    }
    
}
