package repository;

import model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository {
        public Connection getConnection() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/XXXXXXXXXXXXX";
            Connection connection = DriverManager.getConnection(url, "root", "");

            return connection;
        }

        public void insere(Contato contato) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("insert into " +
                    "contato values(null, ?");
            stmt.setString(2, contato.getNome());

            int i = stmt.executeUpdate();
            System.out.println(i + " linhas inseridas");
            connection.close();
        }

        public List<Contato> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
            List<Contato> listContato = new ArrayList<>();
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("select * from contato WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            connection.close();
            return listContato;
        }

        public List<Contato> busca() throws SQLException, ClassNotFoundException {
            List<Contato> listContato = new ArrayList<>();
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("select * from contato");
            ResultSet resultSet = stmt.executeQuery();

            connection.close();
            return listContato;
        }


        public Integer proximoId() throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("select max(id) from contato");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            }
            return 1;
        }


        public void update(Contato contato) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("update contato " +
                    "SET id = ?, nome = ? WHERE id = ?");
            stmt.setInt(1, contato.getId());
            stmt.setString(2, contato.getNome());

            int i = stmt.executeUpdate();
            System.out.println(i + " linhas atualizadas");
            connection.close();
        }


        public void delete(Contato contato) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM contato" +
                    " WHERE id = ?");
            stmt.setInt(1, contato.getId().intValue());
            stmt.executeUpdate();
            connection.close();
        }

    }

