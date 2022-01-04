import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, IOException {
        ArrayList<Object> instances = new ArrayList<>();
        Converter converter = new Converter();
        Ticket ballet = new Ticket(1, "Балет 'Щелкунчик'", "15/01/2022", "19:00",
                "6000 руб.", "Бенуар", 4, 22);
        Ticket opera = new Ticket(2, "Опера 'Иван Сусанин'", "16/01/2022", "19:00",
                "6000 руб.", "Бенуар", 3, 20);

        Path file = Paths.get("MyXML.xml");

        instances.add(ballet);
        instances.add(opera);

        converter.reflection(instances);
        converter.writeXML(file);
    }
}
