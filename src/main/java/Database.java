import java.sql.DriverManager;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Connection connection;
    private static Statement statement;

    public static void connection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/countries2015db.db");
        statement = connection.createStatement();
    }

    public static void init() throws SQLException {
        statement.execute(
                "CREATE TABLE IF NOT EXISTS Country (" +
                "country TEXT PRIMARY KEY, " +
                "region TEXT, " +
                "happinessRank INTEGER, " +
                "happinessScore FLOAT, " +
                "standardError FLOAT, " +
                "economy FLOAT, " +
                "dystopia FLOAT);"
        );

        statement.execute(
                "CREATE TABLE IF NOT EXISTS People (" +
                "country TEXT, " +
                "family FLOAT, " +
                "health FLOAT, " +
                "trust FLOAT, " +
                "generosity FLOAT, " +
                "freedom FLOAT);"
        );
    }

    public static void putData(Country country) {
        String query1 = String.format(
                "INSERT INTO Country (" +
                "country, region, happinessRank, happinessScore, standardError, economy, dystopia) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s'); ",
                country.getCountry(),
                country.getRegion(),
                country.getHappinesRank(),
                country.getHappinesScore(),
                country.getStandardError(),
                country.getEconomy(),
                country.getDystopia()
        );

        String query2 = String.format(
                "INSERT INTO People (" +
                "country, family, health, trust, generosity, freedom) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                country.getCountry(),
                country.getFamily(),
                country.getHealth(),
                country.getFreedom(),
                country.getGenerosity(),
                country.getTrust()
        );
        try {
            statement.execute(query1);
            statement.execute(query2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }

    public static Map<String, Double> getEconomyByCountry() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT country, economy from Country");
        Map<String, Double> economyByCountry = new HashMap<>();

        while (rs.next()) {
            economyByCountry.put(rs.getString("country"), Double.parseDouble(rs.getString("economy")));
        }
        return economyByCountry;
    }

    public static void getCountryWithHighEconomy() throws SQLException {
        ResultSet rs = statement.executeQuery(
                "SELECT country, MAX(economy) " +
                "FROM Country WHERE region IN ('Latin America and Caribbean', 'Eastern Asia')"
        );
        System.out.println("Задание 2\nСтрана с самой высокой экономикой среди 'Latin America and Caribbean' и 'Eastern Asia':");
        System.out.println(rs.getString("country") + "\n");
    }

    public static void getCountryWithAverageIndicators() throws SQLException {
        String query =
                "SELECT country, AVG(avgCountryValue) " +
                "FROM (SELECT country, " +
                "             (AVG(happinessRank) + " +
                "              AVG(happinessScore) + " +
                "              AVG(standardError) + " +
                "              AVG(economy) + " +
                "              AVG(dystopia) + " +
                "              AVG(family) + " +
                "              AVG(health) + " +
                "              AVG(trust) + " +
                "              AVG(generosity) + " +
                "              AVG(freedom)) / 10.0 AS avgCountryValue " +
                "      FROM (SELECT * " +
                "            FROM Country C " +
                "                     JOIN People P ON C.country = P.country " +
                "            WHERE region IN ('Western Europe', 'North America')) " +
                "      GROUP BY country);";

        ResultSet rs = statement.executeQuery(query);
        System.out.println("Задание 3\nСтрана с самыми средними показателями среди 'Western Europe' и 'North America':");
        System.out.println(rs.getString("country") + "\n");
    }
}
