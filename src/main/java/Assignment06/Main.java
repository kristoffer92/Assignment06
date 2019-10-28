package Assignment06;

import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        CityDaoJDBC cityDao = new CityDaoJDBC();
        List<City> city = cityDao.findByName("Kabul");
        city.forEach(System.out::println);
    }
}
