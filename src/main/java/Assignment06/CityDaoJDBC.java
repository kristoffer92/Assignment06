package Assignment06;

import Assignment06.Info.Connect;

import java.sql.*;
import java.util.List;

public class CityDaoJDBC implements CityDao{

    @Override
    public City findById(int id)
    {
        return null;
    }

    @Override
    public List<City> findByCode(String code)
    {
        return null;
    }

    @Override
    public List<City> findByName(String name)
    {
        return null;
    }

    @Override
    public List<City> findByAll()
    {
        return null;
    }

    @Override
    public City add(City city)
    {
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement;
                ResultSet resultSet = statement.executeQuery();

                ) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public City update(City city)
    {
        return null;
    }

    @Override
    public int delete(City city)
    {
        return 0;
    }
}
