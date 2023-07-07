package repository;

import model.Contato;
import model.Startups;
import model.TipoContato;

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
                int comprimento = contato.getNome().length();
                int inicio = comprimento - 6;
                String parte = contato.getNome().substring(inicio);
                if (contato.getNome().contains(parte)) {



                    contatosFiltrados.add(contato);
                }
            }
            return contatosFiltrados;
        }

        public Object[] findContatosInArray() throws SQLException, ClassNotFoundException {
            List<Contato> contatoList = buscarTodos();
            List<String> nomeContatos = new ArrayList<>();
            TipoContatoDAO tipoContatoDAO = new TipoContatoDAO();
            List<TipoContato> listaTipo = tipoContatoDAO.buscarTodos();

            StartupDAO startupDAO = new StartupDAO();
            List<Startups> listaStartups = startupDAO.buscarTodos();


            for (Contato contato : contatoList) {
                String contatos = startupDAO.buscarPorCodigo(contato.getIdStartup()).toString() + " - " + tipoContatoDAO.buscarPorCodigo(contato.getIdTipoContato()).toString()+ "Contato: " + contato.getNome();
                nomeContatos.add(contatos);
            }

            return nomeContatos.toArray();
        }

}
