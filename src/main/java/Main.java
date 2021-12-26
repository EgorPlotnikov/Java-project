import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<Country> countries = Parser.stringProcessing();
        Database.connection();
        Database.init();
        //countries.forEach(Database::putData); // Если таблицы пустые
        Database.getCountryWithHighEconomy();
        Database.getCountryWithAverageIndicators();
        Map<String, Double> dataset = Database.getEconomyByCountry();
        EventQueue.invokeLater(() -> {
            Plot bc = new Plot(dataset);
            bc.setVisible(true);
        });
    }
}
