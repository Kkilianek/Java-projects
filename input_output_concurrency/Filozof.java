package input_output_concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Filozof implements Runnable {
    private Object widelecLewy;
    private Object widelecPrawy;

    public Filozof(Object lewy, Object prawy) {
        this.widelecLewy = lewy;
        this.widelecPrawy = prawy;
    }

    private void zrobCos(String action) throws InterruptedException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String czas = sdf.format(date);
        System.out.println(Thread.currentThread().getName() + " " +
                czas + " " + action);
        Thread.sleep(((int) (Math.random() * 100))); //dana czynność zajmuje filozofowi określoną, losową ilość czasu
    }

    @Override
    public void run() {
        try {
//Używamy synchronize, żeby zablokować dla danego wątku obiekty widelców
            while (true) {
                zrobCos(": Zaduma");
                synchronized (widelecLewy) {
                    zrobCos(": Podniosłem lewy widelec");
                    synchronized (widelecPrawy) {
                        zrobCos(": Podniosłem prawy widelec - mogę jeść");
                        zrobCos(": Odkładam prawy widelec");
                    }
                    zrobCos(": Odkładam lewy widelec. Wracam do zadumy.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
