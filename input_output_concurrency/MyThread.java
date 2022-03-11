package input_output_concurrency;

public class MyThread extends Thread {
    String name;

    public MyThread(String name) {
        super(name);
        this.name = name;
    }

    static void uruchomWatki(int N) {
        for (int i = 1; i <= N; i++) {
            MyThread thread = new MyThread("wątek nr " + i);
            thread.start();
        }
    }
//Wariant pierwszy
//    @Override
//    public void run() {
//        super.run();
//        System.out.println(this.getName());
//    }
    //Wariant drugi - wyswietlanie p razy nazwy danego wątku
//    @Override
//    public void run() {
//        super.run();
//        for (int p = 0; p < 10; p++) {
//            System.out.println(this.getName());
//        }
//    }
    //Wariant trzeci - nieskonczone wyswietlanie nazwy watku
    @Override
    public void run() {
        super.run();
        while (true){
            System.out.println(this.getName());
        }
    }
}
