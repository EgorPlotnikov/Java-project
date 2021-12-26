import java.util.ArrayList;

public class Country {
    private final String country;
    private final String region;
    private final int happinesRank;
    private final double happinesScore;
    private final double standardError;
    private final double economy;
    private final double family;
    private final double health;
    private final double freedom;
    private final double trust;
    private final double generosity;
    private final double dystopia;

    public Country(String country, String region, int rank, ArrayList<Double> values) {
        this.country = country;
        this.region = region;
        this.happinesRank = rank;
        this.happinesScore = values.get(0);
        this.standardError = values.get(1);
        this.economy = values.get(2);
        this.family = values.get(3);
        this.health = values.get(4);
        this.freedom = values.get(5);
        this.trust = values.get(6);
        this.generosity = values.get(7);
        this.dystopia = values.get(8);
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public int getHappinesRank() {
        return happinesRank;
    }

    public double getHappinesScore() {
        return happinesScore;
    }

    public double getStandardError() {
        return standardError;
    }

    public double getEconomy() {
        return economy;
    }

    public double getFamily() {
        return family;
    }

    public double getHealth() {
        return health;
    }

    public double getFreedom() {
        return freedom;
    }

    public double getTrust() {
        return trust;
    }

    public double getGenerosity() {
        return generosity;
    }

    public double getDystopia() {
        return dystopia;
    }
}
