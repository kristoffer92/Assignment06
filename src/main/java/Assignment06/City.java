package Assignment06;

import java.util.Objects;

public class City {
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;

    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public City(String name, String countryCode, String district, int population)
    {
        this(0,name,countryCode,district,population);
    }


    //Getters
    public int getId() {return id;}

    public String getName() {return name;}

    public String getCountryCode() {return countryCode;}

    public String getDistrict() {return district;}

    public int getPopulation() {return population;}

    //Setters
    public void setName(String name) {this.name = name;}

    public void setCountryCode(String countryCode) {this.countryCode = countryCode;}

    public void setDistrict(String district) {this.district = district;}

    public void setPopulation(int population) {this.population = population;}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                population == city.population &&
                Objects.equals(name, city.name) &&
                Objects.equals(countryCode, city.countryCode) &&
                Objects.equals(district, city.district);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, countryCode, district, population);
    }

    @Override
    public String toString() {
        return "City" +
                "\nID: " + id +
                "\nName: " + name +
                "\nCountryCode: " + countryCode +
                "\nDistrict: " + district +
                "\nPopulation: " + population;
    }
}//End of Class
