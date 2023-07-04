package repository;

import model.Startups;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StartupRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/XXXXXXXXXXXXX";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere(Startups startups) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into startup values()");

    }

    public List<Startups> buscaPorID(Long id) throws SQLException,ClassNotFoundException{
        List<Startups> startups = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from startup where idStartup = ?");
        stmt.setLong(1, id);

        ResultSet resultSet =stmt.executeQuery();

        while (resultSet.next()){
            Startups startupsAux = new Startups();
            startupsAux.setId(resultSet.getLong(1));
            startups.add(startupsAux);
        }

        connection.close();
        return startups;

    }

    public Integer proximoId() throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(idStartup) from startup");
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update(Startups startups) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update startup " +
           "set nome = ? where id = ?" );

        stmt.setString(1, startups.getNomeStartup());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas!");
        connection.close();
    }

    public void delete(Startups startups) throws  SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("delete from startup where id = ?");
        stmt.setInt(1, startups.getId().intValue());
    }

    public List<Startups> busca() throws SQLException,ClassNotFoundException{
        List<Startups> startups = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from startup");

        ResultSet resultSet =stmt.executeQuery();

        while (resultSet.next()){
            Startups startupsAux = new Startups();
            startupsAux.setId(resultSet.getLong(1));
            startups.add(startupsAux);
        }

        connection.close();
        return startups;

    }
}
