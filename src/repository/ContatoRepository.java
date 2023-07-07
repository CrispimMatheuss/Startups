package repository;

import model.Contato;
import model.Startups;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository {
        public Connection getConnection() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/startup";
            Connection connection = DriverManager.getConnection(url, "root", "");

            return connection;
        }

        public void insere(Contato contato) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("insert into " +
                    "contato(id_contato, nome, idstartup, id) values(?, ?, ?, ?)");
            stmt.setInt(1, contato.getId());
            stmt.setString(2, contato.getNome());
            stmt.setInt(3, contato.getIdStartup());
            stmt.setInt(4, contato.getIdTipoContato());

            int i = stmt.executeUpdate();
            if (i > 0){
                JOptionPane.showMessageDialog(null, "Contato inserido!");
            }
            connection.close();
        }

        public List<Contato> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
            List<Contato> listContato = new ArrayList<>();
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("select * from contato where id_contato = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Contato contatoAux = new Contato();
                contatoAux.setId(resultSet.getInt(1));
                listContato.add(contatoAux);
            }

            connection.close();
            return listContato;
        }

        public List<Contato> busca() throws SQLException, ClassNotFoundException {
            List<Contato> listContato = new ArrayList<>();
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("select * from contato");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Contato contatoAux = new Contato();
                contatoAux.setId(resultSet.getInt(1));
                contatoAux.setNome(resultSet.getString(2));
                contatoAux.setIdStartup(resultSet.getInt(3));
                contatoAux.setIdTipoContato(resultSet.getInt(4));
                listContato.add(contatoAux);
            }

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
                    "SET nome = ?, idstartup = ?, id = ? WHERE id_contato = ?");
            stmt.setString(1, contato.getNome());
            stmt.setInt(2, contato.getIdStartup());
            stmt.setInt(3, contato.getIdTipoContato());
            stmt.setInt(4, contato.getId());

            int i = stmt.executeUpdate();
            if (i > 0){
                JOptionPane.showMessageDialog(null, "Contato atualizado!");
            }
            connection.close();
        }


        public void delete(Contato contato) throws SQLException, ClassNotFoundException {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM contato" +
                    " where id_contato = ?");
            stmt.setInt(1, contato.getId().intValue());
            int i = stmt.executeUpdate();
            if (i > 0){
                JOptionPane.showMessageDialog(null, "Contato excluido!");
            }
            connection.close();
        }

    }

