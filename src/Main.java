import model.*;
import model.Usuario;
import repository.CidadeDAO;
import repository.StartupDAO;
import repository.UsuarioDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Object usuarioLogado = chamaSelecaoUsuario();
        checaSenhaUsuario(usuarioLogado);
    }

    private static Object chamaSelecaoUsuario() {
        Object[] selectionValues = UsuarioDAO.findUsuariosSistemaInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o usuario?",
                "Cadastro de Startups", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        return selection;
    }

    private static void checaSenhaUsuario(Object usuarioLogado) throws SQLException, ClassNotFoundException {
        String senhaDigitada = JOptionPane.showInputDialog(null,
                "Informe a senha do usuario (" + usuarioLogado + ")");
        Usuario usuarioByLogin = UsuarioDAO.findUsuarioByLogin((String) usuarioLogado);

        if (usuarioByLogin.getSenha().equals(senhaDigitada)) {
            chamaMenuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Senha incorreta!");
            checaSenhaUsuario(usuarioLogado);
        }
    }

    private static void chamaMenuPrincipal() throws SQLException, ClassNotFoundException {
        String[] opcoesMenu = {"Cadastros", "Processos", "Relatorios", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros
                chamaMenuCadastros();
                break;
            case 1: //Processos
                //chamaMenuProcessos();
                break;
            case 2: //Relatorios
                //chamaMenuRelatorios();
                break;
            case 3: //SAIR
                System.exit(0);
                break;
        }
    }

    private static void chamaMenuCadastros() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuCadastro = {"Startups", "Cidades", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //Startups
                Startups startups = chamaCadastroStartup();
                if (startups != null) getStartupDAO().salvar(startups);
                chamaMenuCadastros();
                break;
            case 1: //Cidades
                Cidade cidade = chamaCadastroCidades();
                if (cidade != null) getCidadeDAO().salvar(cidade);
                chamaMenuCadastros();
                break;
            case 2: //Seguro
                /*Seguro seguro = chamaCadastroSeguro();
                if (seguro != null) getSeguroDAO().salvar(seguro);*/
                chamaMenuCadastros();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static Integer chamaOpcaoCrud() {
        String[] opcao = {"Inserção", "Alteração", "Exclusão"};
        int tipoOpcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Operação no cadastro: ",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
        return tipoOpcao;
    }
    private static Startups chamaCadastroStartup() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Startups startups = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                startups = cadastraStartup();
                break;
            case 1: //Alteração
//                startups = selecaoDePessoa();
//                startups = editaPessoa(pessoa);
                break;
            default: //Exclusão
//                startups = selecaoDePessoa();
//                getPessoaDAO().remover(pessoa);
//                pessoa = null;
                break;
        }
        return startups;
    }

    public static Cidade chamaCadastroCidades() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Cidade cidade = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                cidade = cadastraCidade();
                break;
            case 1: //Alteração
                cidade = selecionaCidade();
                cidade = editaCidade(cidade);
                break;
            default: //Exclusão
                cidade = selecionaCidade();
                getCidadeDAO().remover(cidade);
                cidade = null;
                break;
        }
        return cidade;
    }

    private static Startups cadastraStartup() throws SQLException, ClassNotFoundException {
        JPopupMenu jPopupMenu;
        JButton button;
        Startups startups = new Startups();

        JTextField nomeStartup = new JTextField();
        JTextField descStartup = new JTextField();
        JTextField dataInicio  = new JTextField();

        //Trazer as cidades
        /*JComboBox<String> cidade = new JComboBox<>();
        CidadeDAO cidadeDAO = getCidadeDAO();
        List<Cidade> listaCidades = cidadeDAO.buscarTodos();
        cidade.setModel(new DefaultComboBoxModel<>(listaCidades.toArray(getCidades().toArray(new String[0]))));
*/
        CidadeDAO cidadeDAO = getCidadeDAO();
        List<Cidade> listaCidades = cidadeDAO.buscarTodos();
        String[] nomesCidades = new String[listaCidades.size()];

        for(int i = 0; i < listaCidades.size(); i++){
            nomesCidades[i] = listaCidades.get(i).getNomeCidade();
        }

        JComboBox<String> cidade = new JComboBox<>(nomesCidades);
//        cidade.setModel(new DefaultComboBoxModel<>(listaCidades.toArray());

        Object[] message = {
                "Nome da Startup: ", nomeStartup,
                "Descricao da Startup: ", descStartup,
                "Data de Criação da Startup: ", dataInicio,
                "Cidade: ", cidade
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Startups", JOptionPane.OK_CANCEL_OPTION);



        String nome = nomeStartup.getText();
        String descricao = descStartup.getText();
        LocalDate dtInicioStartup = LocalDate.parse(dataInicio.getText());
        return startups;

    }

    private static Cidade cadastraCidade(){
        JPopupMenu jPopupMenu;
        JButton button;
        Cidade cidades = new Cidade();

        JTextField nomeCidade = new JTextField();

        DefaultComboBoxModel<Estados> estadoEnum = new DefaultComboBoxModel<>(Estados.values());
        JComboBox<Estados> comboBoxEstado = new JComboBox<>(estadoEnum);

        Object[] message = {
                "Nome da Cidade: ", nomeCidade,
                "Estado: ", comboBoxEstado
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Cidades", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeCidade.getText();
        Estados uf = (Estados) estadoEnum.getSelectedItem();

        cidades.setNomeCidade(nome);
        cidades.setEstados(Estados.valueOf(String.valueOf(uf)));

        return cidades;

    }

    private static List<String> getCidades() {
        List<String> cidades = new ArrayList<>();
        cidades.add("Criciúma");
        cidades.add("Içara");
        // Adicione mais cidades se necessário
        return cidades;
    }

    public static StartupDAO getStartupDAO(){
        StartupDAO startupDAO = new StartupDAO();
        return startupDAO;
    }

    public static CidadeDAO getCidadeDAO(){
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO;
    }

    public static Cidade selecionaCidade(){
        Object[] selectionValues = new Object[0];
        try {
            selectionValues = getCidadeDAO().findCidadeInArray();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String initialSelection = (String) selectionValues[0];
            Object selection = JOptionPane.showInputDialog(null, "Selecione a Cidade", "Startup APP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
            List<Cidade> cidades = getCidadeDAO().buscarPorNome((String) selection);

        return cidades.get(0);
    }

    private static Cidade editaCidade(Cidade cidade){
        JPopupMenu jPopupMenu;
        JButton button;
        Cidade cidades = new Cidade();

        JTextField nomeCidade = new JTextField();

        DefaultComboBoxModel<Estados> estadoEnum = new DefaultComboBoxModel<>(Estados.values());
        JComboBox<Estados> comboBoxEstado = new JComboBox<>(estadoEnum);

        Object[] message = {
                "Nome Anterior: " + cidade.getNomeCidade(),
                "Nome da Cidade: ", nomeCidade,
                "Estado: ", comboBoxEstado
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Cidades", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeCidade.getText();
        Estados uf = (Estados) estadoEnum.getSelectedItem();

        cidades.setId(cidade.getId());
        cidades.setNomeCidade(nome);
        cidades.setEstados(Estados.valueOf(String.valueOf(uf)));

        return cidades;

    }



}