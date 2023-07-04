package repository;

import model.Cidade;
import model.Estados;
import model.Startups;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/startup";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere(Cidade cidades) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into cidade values(?, ?, ?)");
        stmt.setInt(1, cidades.getId().intValue());
        stmt.setString(2, cidades.getNomeCidade());
        stmt.setString(3, cidades.getEstados().getUF());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Cidade> busca() throws SQLException, ClassNotFoundException{
        List<Cidade> cidades = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from cidade");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Cidade cidadeAux = new Cidade();
            cidadeAux.setId(resultSet.getLong(1));
            cidadeAux.setNomeCidade(resultSet.getString(2));
            cidadeAux.setEstados(Estados.valueOf(resultSet.getString(3)));
            cidades.add(cidadeAux);
        }

        connection.close();
        return cidades;
    }

    public Integer proximoId() throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(idcidade) from cidade");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update(Cidade cidade) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update cidade set nome = ?, uf = ? where id = ?");
        stmt.setString(1, cidade.getNomeCidade());
        stmt.setString(2, cidade.getEstados().getUF());
        stmt.setInt(3, cidade.getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }

    public void delete(Cidade cidade) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("delete from cidade where id = ?");

        stmt.setInt(1, cidade.getId().intValue());

        stmt.executeUpdate();

        connection.close();
    }


}
