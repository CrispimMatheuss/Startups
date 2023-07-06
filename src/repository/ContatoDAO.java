package repository;

import model.Contato;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements IGenericDAO<Contato> {
        static List<Contato> contatos = new ArrayList<>();

        @Override
        public void salvar(Contato objeto) {
            ContatoRepository contatoRepository = new ContatoRepository();
            try {
                if (objeto.getId() != null) {
                    contatoRepository.update(objeto);
                } else {
                    objeto.setId(contatoRepository.proximoId().intValue());
                    contatoRepository.insere(objeto);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            contatos.add(objeto);
        }

        @Override
        public void remover(Contato objeto) throws SQLException, ClassNotFoundException {
            ContatoRepository contatoRepository = new ContatoRepository();
            contatoRepository.delete(objeto);
        }

        @Override
        public List<Contato> buscarTodos() {
            System.out.println(contatos);

            ContatoRepository contatoRepository = new ContatoRepository();
            try {
                contatos = contatoRepository.busca();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return contatos;
        }

        @Override
        public List<Contato> buscarPorNome(String nome) {
            List<Contato> contatosFiltrados = new ArrayList<>();
            for (Contato contato : contatos) {
                if (contato.getNome().contains(nome)) {
                    contatosFiltrados.add(contato);
                }
            }
            return contatosFiltrados;
        }

        public Object[] findContatosInArray() {
            List<Contato> contatoList = buscarTodos();
            List<String> nomeContatos = new ArrayList<>();

            for (Contato contato : contatoList) {
                nomeContatos.add(contato.getNome());
            }

            return nomeContatos.toArray();
        }

}
