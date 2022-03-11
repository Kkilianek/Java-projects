package input_output_concurrency;

import java.io.IOException;
public class CSVConvert {

    public static void main(String[] args) throws IOException {
        CSV object = new CSV(args[0], args[1], args[2], args[3]);
        //testowe sprawdzenie poprawności zapisu do listy
        System.out.println(object);
        System.out.println(object.getValue(6, 2)); //test metody getValue
        object.write();

    }
}
