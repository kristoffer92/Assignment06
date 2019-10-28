package Assignment06;

import Assignment06.Info.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{


    private static final String CREATE_CITY = "INSERT INTO city (Name, CountryCode, District, Population) VALUES(?, ?, ?, ?)";
    private static final String FIND_CITY_BY_ID = "SELECT * FROM city WHERE ID = ?";
    private static final String UPDATE_CITY = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ?, WHERE ID = ?";
    private static final String DELETE_CITY = "DELETE FROM city WHERE ID = ?";
    private static final String FIND_CITIES_BY_CODE = "SELECT * FROM city WHERE CountryCode = ?";
    private static final String FIND_CITIES_BY_NAME = "SELECT * FROM city WHERE Name = ?";
    private static final String FIND_ALL_CITIES = "SELECT * FROM city";


    //makeMethods
    private PreparedStatement makeFindById(Connection connection, int id) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(FIND_CITY_BY_ID);
        statement.setInt(1, id);
        return statement;
    }

    private PreparedStatement makeFindByName(Connection connection, String name) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(FIND_CITIES_BY_NAME);
        statement.setString(1, name);
        return statement;
    }

    private PreparedStatement makeFindByCode(Connection connection, String code) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(FIND_CITIES_BY_CODE);
        statement.setString(1, code);
        return statement;
    }

    private PreparedStatement makeFindAllCities(Connection connection) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(FIND_ALL_CITIES);
        return statement;
    }

    private PreparedStatement makeCity(Connection connection, City city) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(CREATE_CITY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, city.getName());
        statement.setString(2, city.getCountryCode());
        statement.setString(3, city.getDistrict());
        statement.setInt(4, city.getPopulation());
        statement.setInt(5, city.getId());
        statement.executeUpdate();
        return statement;
    }

    private PreparedStatement makeUpdate(Connection connection, City city) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(UPDATE_CITY);
        statement.setString(1, city.getName());
        statement.setString(2, city.getCountryCode());
        statement.setString(3, city.getDistrict());
        statement.setInt(4, city.getPopulation());
        statement.setInt(5, city.getId());
        return statement;
    }

    private PreparedStatement makeDelete(Connection connection, City city) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(DELETE_CITY);
        statement.setInt(1, city.getId());
        return statement;
    }

    //ResultSet
    private City cityFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getInt("ID"),
                resultSet.getString("Name"),
                resultSet.getString("CountryCode"),
                resultSet.getString("District"),
                resultSet.getInt("Population"));
    }


    //Finding Id/Name/CountryCode/All
    @Override
    public City findById(int id)
    {
        City found = null;
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement = makeFindById(connection, id);
                ResultSet resultSet = statement.executeQuery()
                ){
            while(resultSet.next())
            {
                found=cityFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public List<City> findByName(String name)
    {
        List<City> found = new ArrayList<>();
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement = makeFindByName(connection, name);
                ResultSet resultSet = statement.executeQuery()
        ){
            while(resultSet.next())
            {
                found.add(cityFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }


    @Override
    public List<City> findByCode(String code)
    {
        List<City> found = new ArrayList<>();
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement = makeFindByCode(connection, code);
                ResultSet resultSet = statement.executeQuery()
        ){
            while(resultSet.next())
            {
                found.add(cityFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }


    @Override
    public List<City> findByAll()
    {
        List<City> found = new ArrayList<>();
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement = makeFindAllCities(connection);
                ResultSet resultSet = statement.executeQuery()
        ){
            while(resultSet.next())
            {
                found.add(cityFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }


    //Making a City
    @Override
    public City add(City city)
    {
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement = makeCity(connection, city);
                ResultSet resultSet = statement.getGeneratedKeys()
        ){
                int cityId = 0;
                while(resultSet.next()) {
                    cityId = resultSet.getInt(1);

                }
                city = new City(
                        cityId,
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }


    //Updating a City
    @Override
    public City update(City city)
    {
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement = makeUpdate(connection, city)
                )
        {
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }


    //Remove a City
    @Override
    public int delete(City city)
    {
        int result = 0;
        try(
                Connection connection = Connect.getConnection();
                PreparedStatement statement = makeDelete(connection, city)
                )
        {
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}//End of Class
