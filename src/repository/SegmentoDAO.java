package repository;

import model.Cidade;
import model.Segmento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SegmentoDAO implements IGenericDAO<Segmento> {
    static List<Segmento> segmentos = new ArrayList<>();

    public void salvar(Segmento segmento) {
        SegmentoRepository segmentoRepository = new SegmentoRepository();
        try {
            if (segmento.getId() != null) {
                segmentoRepository.update(segmento);
            } else {
                segmento.setId(segmentoRepository.proximoId().intValue());
                segmentoRepository.insere(segmento);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        segmentos.add(segmento);
    }

    public void remover(Segmento segmento) throws SQLException, ClassNotFoundException {
        SegmentoRepository segmentoRepository = new SegmentoRepository();
        segmentoRepository.delete(segmento);
    }

    public List<Segmento> buscarTodos() {
        System.out.println(segmentos);

        SegmentoRepository segmentoRepository = new SegmentoRepository();
        try {
            segmentos = segmentoRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return segmentos;
    }


    public List<Segmento> buscarPorNome(String nome) {
        List<Segmento> segmentosFiltrados = new ArrayList<>();
        for (Segmento segmento : segmentos) {
            if (segmento.getNome().contains(nome)) {
                segmentosFiltrados.add(segmento);
            }
        }
        return segmentosFiltrados;
    }

    public Object[] findSegmentosInArray() {
        List<Segmento> segmentoList = buscarTodos();
        List<String> nomeSegmentos = new ArrayList<>();

        for (Segmento segmento : segmentoList) {
            nomeSegmentos.add(segmento.getNome());
        }

        return nomeSegmentos.toArray();
    }

}

