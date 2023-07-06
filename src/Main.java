import model.*;
import model.Usuario;
import repository.*;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        String[] opcoesMenu = {"Cadastros", "Relatorios", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros
                chamaMenuCadastros();
                break;
            case 1: //Relatorios
                //chamaMenuRelatorios();
                break;
            case 2: //SAIR
                int opcaoSair= JOptionPane.showOptionDialog(null, "Deseja realmente sair?",
                        "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (opcaoSair == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    chamaMenuPrincipal();
                }
                break;
        }
    }

    private static void chamaMenuCadastros() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuCadastro = {"Startups", "Cidades", "Segmentos", "Contatos", "Voltar"};
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
            case 2: //Segmentos
                Segmento segmento = chamaCadastroSegmento();
                if (segmento != null) getSegmentoDAO().salvar(segmento);
                chamaMenuCadastros();
                break;
            case 3: //Contatos
                Contato contato = chamaCadastroContato();
                if (contato != null) getContatoDAO().salvar(contato);
                chamaMenuCadastros();
                break;
            case 4: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static Integer chamaOpcaoCrud() {
        String[] opcao = {"Inserção", "Alteração", "Exclusão", "Voltar"};
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
                startups = selecionaStartup();
                startups = editaStartup(startups);
                break;
            case 2: //Exclusão
                startups = selecionaStartup();
                getStartupDAO().remover(startups);
                startups = null;
                break;
            default: //Voltar
                chamaMenuCadastros();
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

            case 2: //Exclusão
                cidade = selecionaCidade();
                getCidadeDAO().remover(cidade);
                cidade = null;
                break;

            default: //Voltar
                chamaMenuCadastros();
                break;

        }
        return cidade;
    }

    private static Segmento chamaCadastroSegmento() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Segmento segmento = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                segmento = cadastraSegmento();
                break;
            case 1: //Alteração
                segmento = selecionaSegmento();
                segmento = editaSegmento(segmento);
                break;
            case 2: //Exclusão
//                segmento = selecionaSegmento();
                getSegmentoDAO().remover(segmento);
                segmento = null;
                break;
            default: //Voltar
                chamaMenuCadastros();
                break;
        }
        return segmento;
    }

    private static Startups cadastraStartup() throws SQLException, ClassNotFoundException {
        JPopupMenu jPopupMenu;
        JButton button;
        Startups startups = new Startups();

        JTextField nomeStartup = new JTextField();
        JTextField descStartup = new JTextField();
        JTextField dataInicio  = new JTextField();
        JTextField rua         = new JTextField();
        JTextField bairro      = new JTextField();
        JTextField descSolucoes= new JTextField();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        CidadeDAO cidadeDAO = getCidadeDAO();
        List<Cidade> listaCidades = cidadeDAO.buscarTodos();
        String[] nomesCidades = new String[listaCidades.size()];

        for(int i = 0; i < listaCidades.size(); i++){
            nomesCidades[i] = listaCidades.get(i).getNomeCidade();
        }

        JComboBox<String> cidade = new JComboBox<>(nomesCidades);

        Object[] message = {
                "Nome da Startup: ", nomeStartup,
                "Descricao da Startup: ", descStartup,
                "Data de Criação da Startup (dd/mm/yyyy): ", dataInicio,
                "Descrição das Soluções: ", descSolucoes,
                "Rua: ", rua,
                "Bairro: ", bairro,
                "Cidade: ", cidade
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Startups", JOptionPane.OK_CANCEL_OPTION);

        int indiceSelecionado = cidade.getSelectedIndex();
        int codigoCidade = listaCidades.get(cidade.getSelectedIndex()).getId().intValue();

        startups.setNomeStartup(nomeStartup.getText());
        startups.setDescStartup(descStartup.getText());
        LocalDate data = LocalDate.parse(dataInicio.getText(), formatter);
        startups.setDataInicio(data);
        startups.setCodigoCidade(codigoCidade);
        startups.setEnderecoStartup(rua.getText() + " - " + bairro.getText());
        startups.setDescSolucoes(descSolucoes.getText());

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

    private static Segmento cadastraSegmento(){

        Segmento segmento = new Segmento();

        JTextField nomeSegmento = new JTextField();

        Object[] message = {
                "Nome do Segmento: ", nomeSegmento};

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Segmentos", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeSegmento.getText();
        segmento.setNome(nome);
        return segmento;

    }
    public static StartupDAO getStartupDAO(){
        StartupDAO startupDAO = new StartupDAO();
        return startupDAO;
    }

    public static CidadeDAO getCidadeDAO(){
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO;
    }

    public static SegmentoDAO getSegmentoDAO(){
        SegmentoDAO segmentoDAO = new SegmentoDAO();
        return segmentoDAO;
    }

    public static ContatoDAO getContatoDAO(){
        ContatoDAO contatoDAO = new ContatoDAO();
        return contatoDAO;
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
                "UF Anterior: " + cidade.getEstados().getUF(),
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


    private static Startups editaStartup(Startups startup) throws SQLException, ClassNotFoundException {
        JPopupMenu jPopupMenu;
        JButton button;
        Startups startups = new Startups();

        JTextField nomeStartup = new JTextField();
        JTextField descStartup = new JTextField();
        JTextField dataInicio  = new JTextField();
        JTextField rua         = new JTextField();
        JTextField bairro      = new JTextField();
        JTextField descSolucoes= new JTextField();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        CidadeDAO cidadeDAO = getCidadeDAO();
        List<Cidade> listaCidades = cidadeDAO.buscarTodos();
        String[] nomesCidades = new String[listaCidades.size()];

        for(int i = 0; i < listaCidades.size(); i++){
            nomesCidades[i] = listaCidades.get(i).getNomeCidade();
        }

        JComboBox<String> cidade = new JComboBox<>(nomesCidades);

        Object[] message = {
                "Nome Anterior: ", startup.getNomeStartup(),
                "Nome da Startup: ", nomeStartup,
                "Descricao da Startup: ", descStartup,
                "Data de Criação da Startup (dd/mm/yyyy): ", dataInicio,
                "Descrição das Soluções: ", descSolucoes,
                "Rua: ", rua,
                "Bairro: ", bairro,
                "Cidade: ", cidade
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Startups", JOptionPane.OK_CANCEL_OPTION);

        int indiceSelecionado = cidade.getSelectedIndex();
        int codigoCidade = listaCidades.get(cidade.getSelectedIndex()).getId().intValue();

        startups.setId(startup.getId());
        startups.setNomeStartup(nomeStartup.getText());
        startups.setDescStartup(descStartup.getText());
        LocalDate data = LocalDate.parse(dataInicio.getText(), formatter);
        startups.setDataInicio(data);
        startups.setCodigoCidade(codigoCidade);
        startups.setEnderecoStartup(rua.getText() + " - " + bairro.getText());
        startups.setDescSolucoes(descSolucoes.getText());

        return startups;

    }


    private static Segmento editaSegmento(Segmento segmento){
        Segmento segmentos = new Segmento();

        JTextField nomeSegmento = new JTextField();

        Object[] message = {
                "Nome Anterior: " + segmento.getNome(),
                "Nome do Segmento: ", nomeSegmento
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Segmentos", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeSegmento.getText();
        segmentos.setId(segmento.getId());
        segmentos.setNome(nome);

        return segmentos;

    }


    public static Startups selecionaStartup(){
            Object[] selectionValues = new Object[0];
            try {
                selectionValues = getStartupDAO().findStartupsInArray();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String initialSelection = (String) selectionValues[0];
                Object selection = JOptionPane.showInputDialog(null, "Selecione a Startup", "Startup APP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
                List<Startups> startups = getStartupDAO().buscarPorNome((String) selection);

            return startups.get(0);
        }

    public static Segmento selecionaSegmento() throws SQLException {
        Object[] selectionValues = new Object[0];
        selectionValues = getSegmentoDAO().findSegmentosInArray();

        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione a Startup", "Startup APP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Segmento> segmento = getSegmentoDAO().buscarPorNome((String) selection);

        return segmento.get(0);
    }



    public static Contato chamaCadastroContato() throws SQLException, ClassNotFoundException {
        Integer opcaoCrud = chamaOpcaoCrud();
        Contato contato = null;
        switch (opcaoCrud) {
            case 0: //Inserção
                contato = cadastraContato();
                break;
            case 1: //Alteração
                contato = selecionaContato();
                contato = editaContato(contato);
                break;

            case 2: //Exclusão
                contato = selecionaContato();
                getContatoDAO().remover(contato);
                contato = null;
                break;

            default: //Voltar
                chamaMenuCadastros();
                break;

        }
        return contato;
    }


    private static Contato cadastraContato(){
        JPopupMenu jPopupMenu;
        JButton button;
        Contato contato= new Contato();

        JTextField nomeContato = new JTextField();

        Object[] message = {
                "Nome do contato: ", nomeContato,
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de contatos", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeContato.getText();

        contato.setNome(nome);

        return contato;

    }

    public static Contato selecionaContato(){
        Object[] selectionValues = new Object[0];
        selectionValues = getContatoDAO().findContatosInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o contato", "Startup APP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Contato> contatoList = getContatoDAO().buscarPorNome((String) selection);

        return contatoList.get(0);
    }

    private static Contato editaContato(Contato contato){
        JPopupMenu jPopupMenu;
        JButton button;
        Contato contato1 = new Contato();

        JTextField nomeContato = new JTextField();

        Object[] message = {
                "Nome anterior: " + contato1.getNome(),
                "Nome do contato: ", nomeContato,
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de contatos", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeContato.getText();

        contato1.setId(contato1.getId());
        contato1.setNome(nome);

        return contato1;
    }



}