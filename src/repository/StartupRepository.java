package repository;

import model.Startups;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StartupRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/startup";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere(Startups startups) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into startup (idstartup, nome, descricao, data_inicio, endereco, desc_solucoes, idcidade, idsegmento) values(?,?,?,?,?,?,?,?)");
        stmt.setInt(1, startups.getId().intValue());
        stmt.setString(2, startups.getNomeStartup());
        stmt.setString(3, startups.getDescStartup());
        stmt.setDate(4, Date.valueOf(startups.getDataInicio()));
        stmt.setString(5, startups.getEnderecoStartup());
        stmt.setString(6, startups.getDescSolucoes());
        stmt.setInt(7, startups.getCodigoCidade());
        stmt.setInt(8, startups.getIdSegmento());

        int i = stmt.executeUpdate();
        if (i > 0){
            JOptionPane.showMessageDialog(null, "Startup inserida!");
        }
        connection.close();

    }

    public List<Startups> buscaPorID(Long id) throws SQLException,ClassNotFoundException{
        List<Startups> startups = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from startup where idStartup = ?");
        stmt.setLong(1, id);

        ResultSet resultSet =stmt.executeQuery();

        while (resultSet.next()){
            Startups startupsAux = new Startups();
            startupsAux.setId(resultSet.getInt(1));
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
           "set nome = ?, descricao = ?, data_inicio = ?, endereco = ?, desc_solucoes = ?, idcidade = ?, idsegmento = ? where idstartup = ?" );

        stmt.setString(1, startups.getNomeStartup());
        stmt.setString(2, startups.getDescStartup());
        stmt.setDate(3, Date.valueOf(startups.getDataInicio()));
        stmt.setString(4, startups.getEnderecoStartup());
        stmt.setString(5, startups.getDescSolucoes());
        stmt.setInt(6, startups.getCodigoCidade());
        stmt.setInt(7, startups.getIdSegmento().intValue());
        stmt.setInt(8, startups.getId().intValue());

        int i = stmt.executeUpdate();
        if (i > 0){
            JOptionPane.showMessageDialog(null, "Startup atualizada!");
        }
        connection.close();
    }

    public void delete(Startups startups) throws  SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("delete from startup where idstartup = ?");
        stmt.setInt(1, startups.getId().intValue());

        int i = stmt.executeUpdate();

        if (i > 0){
            JOptionPane.showMessageDialog(null, "Startup excluida!");
        }

        connection.close();
    }

    public List<Startups> busca() throws SQLException,ClassNotFoundException{
        List<Startups> startups = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from startup");

        ResultSet resultSet =stmt.executeQuery();

        while (resultSet.next()){
            Startups startupsAux = new Startups();
            startupsAux.setId(resultSet.getInt(1));
            startupsAux.setNomeStartup(resultSet.getString(2));
            startupsAux.setDescStartup(resultSet.getString(3));
            startupsAux.setDataInicio(resultSet.getDate(4).toLocalDate());
            startupsAux.setEnderecoStartup(resultSet.getString(5));
            startupsAux.setDescSolucoes(resultSet.getString(6));
            startupsAux.setCodigoCidade(resultSet.getInt(8));
            startups.add(startupsAux);
        }

        connection.close();
        return startups;

    }
}
