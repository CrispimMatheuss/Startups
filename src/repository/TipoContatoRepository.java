package repository;

import model.Segmento;
import model.TipoContato;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContatoRepository {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/startup";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere (TipoContato tipoContato) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into tipocontato values(null, ?)");
        stmt.setString(1, tipoContato.getNome());

        int i = stmt.executeUpdate();
        if (i > 0){
            JOptionPane.showMessageDialog(null, "Tipo de Contato inserido!");
        }
        connection.close();
    }

    public List<TipoContato> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
        List<TipoContato> tipoContatos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM TipoContato WHERE idTipoContato = ?");
        stmt.setLong(1, id);

        ResultSet resultSet = stmt.executeQuery();

        connection.close();
        return tipoContatos;
    }

    public List<TipoContato> busca() throws SQLException, ClassNotFoundException {
        List<TipoContato> tipoContato = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM TipoContato");
        ResultSet resultSet = stmt.executeQuery();

        connection.close();
        return tipoContato;
    }

    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT max(id) FROM TipoContato");

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update(TipoContato tipoContato) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update tipocontato set id = ?, nome = ? where id = ?");

        stmt.setInt(1, tipoContato.getId());
        stmt.setString(2, tipoContato.getNome());

        int i = stmt.executeUpdate();
        if (i > 0){
            JOptionPane.showMessageDialog(null, "Tipo de Contato atualizado!");
        }
        connection.close();
    }

    public void delete(TipoContato tipoContato) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        int count = 0;

        PreparedStatement stmt2 = connection.prepareStatement("select count(*) from contato where id = ?");
        stmt2.setInt(1, tipoContato.getId().intValue());
        ResultSet resultSet = stmt2.executeQuery();

        while(resultSet.next()){
            count = resultSet.getInt(1);
            if (count > 0){
                JOptionPane.showMessageDialog(null, "Não é possível excluir o tipo de contato pois ele está vinculada à algum contato!");
            }
        }

        if (count ==0) {

            PreparedStatement stmt = connection.prepareStatement("DELETE FROM TipoContato" +
                    " WHERE id = ?");
            stmt.setInt(1, tipoContato.getId().intValue());
            int i = stmt.executeUpdate();
            if (i > 0){
                JOptionPane.showMessageDialog(null, "Tipo de contato excluído!");
            }
            connection.close();
        }
    }
}
