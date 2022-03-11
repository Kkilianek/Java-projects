package input_output_concurrency;

public class Zadanie4 {
    public static void main(String[] args) {
        final Filozof[] filozofowie = new Filozof[5];
        Object[] widelce = new Object[filozofowie.length];

        for (int i = 0; i < widelce.length; i++) {
            widelce[i] = new Object();
        }

        for (int i = 0; i < filozofowie.length; i++) {
            Object lewywidelec = widelce[i];
            Object prawywidelec = widelce[(i + 1) % widelce.length];//Widelec prawy jest kolejnym widelcem w stosunku do lewego, ale żeby móc określić wiele prawych widelców korzystamy z modulo
            // spr:
//            System.out.println(i+1% widelce.length);
            // Ostatni filozof, musi najpierw podnieść prawy widelec, ponieważ jeśli spróbuje wziąć lewy (a takiego dostępnego nie ma), to proces nie pójdzie dalej
            //Można to dobrze zobaczyć na rysunku np. tego z wikipedii
            if (i == filozofowie.length - 1) {
                filozofowie[i] = new Filozof(prawywidelec, lewywidelec);
            } else {
                filozofowie[i] = new Filozof(lewywidelec, prawywidelec);
            }

            Thread thread = new Thread(filozofowie[i], "input_output_concurrency.Filozof nr " + (i + 1));
            thread.start();
        }
    }
}
