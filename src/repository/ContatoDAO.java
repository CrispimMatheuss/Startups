package repository;

import model.Contato;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
        static List<Contato> contatos = new ArrayList<>();

        public void salvar(Contato contato) {
            ContatoRepository contatoRepository = new ContatoRepository();
            try {
                if (contato.getId() != null) {
                    contatoRepository.update(contato);
                } else {
                    contato.setId(contatoRepository.proximoId().intValue());
                    contatoRepository.insere(contato);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            contatos.add(contato);
        }


        public void remover(Contato contato) throws SQLException, ClassNotFoundException {
            ContatoRepository contatoRepository = new ContatoRepository();
            contatoRepository.delete(contato);
        }


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
