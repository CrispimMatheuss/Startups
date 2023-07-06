package repository;

import model.Cidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CidadeDAO implements IGenericDAO<Cidade> {

    static List<Cidade> cidades = new ArrayList<>();

    @Override
    public void salvar(Cidade objeto) {
        CidadeRepository cidadeRepository = new CidadeRepository();

        try{
            if (objeto.getId() != null){
                cidadeRepository.update(objeto);
            } else {
                objeto.setId(cidadeRepository.proximoId().intValue());
                cidadeRepository.insere(objeto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        cidades.add(objeto);
    }

    @Override
    public void remover(Cidade objeto) throws SQLException, ClassNotFoundException {
        CidadeRepository cidadeRepository = new CidadeRepository();
        cidadeRepository.delete(objeto);
    }

    @Override
    public List<Cidade> buscarTodos() throws SQLException, ClassNotFoundException {
        System.out.println(cidades);
        CidadeRepository cidadeRepository = new CidadeRepository();
        try{
            cidades = cidadeRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cidades;
    }

    @Override
    public List<Cidade> buscarPorNome(String nome) {
        List<Cidade> cidadesFiltradas = new ArrayList<>();
        for (Cidade cidade : cidades){
            if (cidade.getNomeCidade().contains(nome)){
                cidadesFiltradas.add(cidade);
            }
        }

        return cidadesFiltradas;
    }

    public Object[] findCidadeInArray() throws SQLException, ClassNotFoundException {
        List<Cidade> cidades = buscarTodos();
        List<String> cidadesNomes = new ArrayList<>();

        for (Cidade cidade : cidades){
            cidadesNomes.add(cidade.getNomeCidade());
        }
        return cidadesNomes.toArray();
    }
}
