import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<Country> stringProcessing() {
        ArrayList<Country> countries = new ArrayList<>();
        try (BufferedReader r = Files.newBufferedReader(Paths.get("src/main/resources/countries2015.csv"))) {
            r.readLine();
            while (r.ready()) {
                String[] data = r.readLine().split(",");
                ArrayList<Double> values = new ArrayList<>();
                for (int i = 3; i < data.length; i++)
                    values.add(Double.parseDouble(data[i]));
                countries.add(new Country(data[0], data[1], Integer.parseInt(data[2]), values)
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
