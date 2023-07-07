package repository;

import model.Startups;
import model.TipoContato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoContatoDAO {
    public static List<TipoContato> tipoContatos = new ArrayList<>();
        public void salvar(TipoContato tipoContato) {
            TipoContatoRepository tipoContatoRepository = new TipoContatoRepository();
            try {
                if (tipoContato.getId() != null) {
                    tipoContatoRepository.update(tipoContato);
                } else {
                    tipoContato.setId(tipoContatoRepository.proximoId().intValue());
                    tipoContatoRepository.insere(tipoContato);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            tipoContatos.add(tipoContato);
    }

    public void remover(TipoContato tipoContato) throws SQLException, ClassNotFoundException {
        TipoContatoRepository tipoContatoRepository = new TipoContatoRepository();
        tipoContatoRepository.delete(tipoContato);
    }

    public List<TipoContato> buscarTodos() {
        System.out.println(tipoContatos);

         TipoContatoRepository tipoContatoRepository = new TipoContatoRepository();
        try {
            tipoContatos = tipoContatoRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return tipoContatos;
    }


    public List<TipoContato> buscarPorNome(String nome) {
        List<TipoContato> tipoContatosFiltrados = new ArrayList<>();
        for (TipoContato tipoContato : tipoContatos) {
            if (tipoContato.getNome().contains(nome)) {
                tipoContatosFiltrados.add(tipoContato);
            }
        }
        return tipoContatosFiltrados;
    }

    public Object[] findTipoContatoInArray() throws SQLException, ClassNotFoundException {
        List<TipoContato> tipoContatos = buscarTodos();
        List<String> tipoContatoNomes = new ArrayList<>();

        for (TipoContato tipoContato : tipoContatos) {
            tipoContatoNomes.add(tipoContato.getNome());
        }
        return tipoContatoNomes.toArray();
    }

    public List<TipoContato> buscarPorCodigo(Integer codigo) {
        List<TipoContato> tipoFiltradas = new ArrayList<>();
        for (TipoContato startup : tipoContatos){
            if (startup.getId() == codigo){
                tipoFiltradas.add(startup);
            }
        }

        return tipoFiltradas;
    }
}


