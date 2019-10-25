package Assignment06;

import java.util.List;

public interface CityDao
{
    City findById(int id);
    List<City> findByCode(String code);
    List<City> findByName(String name);
    List<City> findByAll();
    City add(City city);
    City update(City city);
    int delete(City city);
}
