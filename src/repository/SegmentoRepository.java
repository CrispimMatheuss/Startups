package repository;

import model.Segmento;
import model.Startups;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SegmentoRepository {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/startup";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere(Segmento segmento) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into " +
                "segmento values (null, ?)");
        //stmt.setInt(1, segmento.getId().intValue());
        stmt.setString(1, segmento.getNome());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Segmento> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
        List<Segmento> segmentoList = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM segmento WHERE id = ?");
        stmt.setLong(1, id);
        ResultSet resultSet = stmt.executeQuery();

        connection.close();
        return segmentoList;
    }

    public List<Segmento> busca() throws SQLException, ClassNotFoundException {
        List<Segmento> segmentoList = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from segmento");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Segmento segmentoAux = new Segmento();
            segmentoAux.setId(resultSet.getInt(1));
            segmentoAux.setNome(resultSet.getString(2));
            segmentoList.add(segmentoAux);
        }

        connection.close();
        return segmentoList;
    }


    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from segmento");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }


    public void update(Segmento segmento) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update segmento " +
                "SET nome = ? WHERE id = ?");
        stmt.setString(1, segmento.getNome());
        stmt.setInt(2, segmento.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }


    public void delete(Segmento segmento) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM segmento" +
                " WHERE id = ?");
        stmt.setInt(1, segmento.getId().intValue());
        stmt.executeUpdate();
        connection.close();
    }

}
