package repository;

import model.Startups;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class StartupDAO implements IGenericDAO<Startups> {

    static List<Startups> startups = new ArrayList<>();
    @Override
    public void salvar(Startups objeto) {
        StartupRepository startupRepository = new StartupRepository();
        try{
            if(objeto.getId() != null){
                startupRepository.update(objeto);
            } else {
                objeto.setId(startupRepository.proximoId().intValue());
                startupRepository.insere(objeto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remover(Startups objeto) throws SQLException, ClassNotFoundException {
        StartupRepository startupRepository = new StartupRepository();
        startupRepository.delete(objeto);
    }

    @Override
    public List<Startups> buscarTodos() throws SQLException, ClassNotFoundException {
        System.out.println(startups);

        StartupRepository startupRepository = new StartupRepository();
        try{
            startups = startupRepository.busca();
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return startups;

    }

    @Override
    public List<Startups> buscarPorNome(String nome) {
        List<Startups> startupFiltrada = new ArrayList<>();
        for (Startups startups1 : startups){
            if (startups1.getNomeStartup().contains(nome)){
                startupFiltrada.add(startups1);
            }
        }
        return startupFiltrada;
    }

    public Object[] findStartupsInArray() throws SQLException, ClassNotFoundException {
        List<Startups> startups = buscarTodos();
        List<String> startupsNomes = new ArrayList<>();

        for (Startups startups1 : startups){
            startupsNomes.add(startups1.getNomeStartup());
        }
        return startupsNomes.toArray();
    }
}