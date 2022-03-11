
package miesiace;

/**
 *
 * @author kacpe
 */

import java.util.Scanner;
public class Miesiace {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Podaj nr miesiąca:");
        
        Scanner scan = new Scanner(System.in);
        int month;
        month = scan.nextInt();
        String season, miesiac;
        switch (month) {
            case 12:
                miesiac = "grudzien";
                season= "zima";
                break;
            case 1:
                miesiac = "styczen";
                season= "zima";
                break;
            case 2:
                miesiac = "luty";
                season= "zima";
                break;
            case 3:
                miesiac = "marzec";
                season= "wiosna";
                break;
            case 4:
                miesiac = "kwiecen";
                season= "wiosna";
                break;
            case 5:
                miesiac = "maj";
                season= "wiosna";
                break;
            case 6:
                miesiac = "czerwiec";
                season= "lato";
                break;
            case 7:
                miesiac = "lipiec";
                season= "lato";
                break;
            case 8:
                miesiac = "sierpien";
                season= "lato";
                break;
            case 9:
                miesiac = "wrzesien";
                season= "jesien";
                break;
            case 10:
                miesiac = "pazdziernik";
                season= "jesien";
                break;
            case 11:
                miesiac = "listopad";
                season= "jesien";
                break;
            default:
                miesiac = "Błędny miesiąc";
                season = "Błąd";
                break;
        }
        System.out.println("Twoj miesiac to " + miesiac + " a jego pora roku to " + season );
        
    }
    
}
