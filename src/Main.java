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
        chamaLogin();
        //Object usuarioLogado = chamaLogin();//chamaSelecaoUsuario();
        //verificarCredenciais(usuarioLogado);
    }

    public static void chamaLogin() throws SQLException, ClassNotFoundException {
        Object[] nomeUsuario = UsuarioDAO.findUsuariosSistemaInArray();

        JComboBox<Object> nomeUsuarioComboBox = new JComboBox<>(nomeUsuario);
        JPasswordField passwordField = new JPasswordField();

        Object[] loginMessage= {"Usuário:", nomeUsuarioComboBox, "Senha:", passwordField};

        int loginOption = JOptionPane.showConfirmDialog(null, loginMessage,
                "Tela de login", JOptionPane.OK_CANCEL_OPTION);

        if (loginOption == JOptionPane.OK_OPTION) {
            Object nomeUser = nomeUsuarioComboBox.getSelectedItem();
            String password = new String(passwordField.getPassword());

            if (verificarCredenciais(nomeUser, password)) {
                verificarCredenciais(nomeUser, password);
                chamaMenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Tente novamente. Usuário e/ou senha inválido.", "Erro de login", JOptionPane.ERROR_MESSAGE);
                chamaLogin();
            }
        } else if (loginOption == JOptionPane.CANCEL_OPTION) {
            int opcaoSair = JOptionPane.showOptionDialog(null,
                    "Deseja realmente sair?",
                    "Confirmação", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (opcaoSair == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                chamaLogin();
            }
        } else {
            System.exit(0);
        }
        //return null;
    }


    public static boolean verificarCredenciais(Object usuarioLogado, String password) {
        try {
            String nomeUsuarioStr = usuarioLogado.toString();
            Usuario usuario = UsuarioDAO.findUsuarioByLogin(nomeUsuarioStr);

            if (usuario != null && usuario.getSenha().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void chamaMenuPrincipal() throws SQLException, ClassNotFoundException {
        String[] opcoesMenu = {"Cidades", "Segmento", "Tipo de contato", "Startups", "Contatos", "Relatórios", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

        switch (opcao) {
            case 0: //Cidades
                Cidade cidades= chamaCadastroCidades();
                if (cidades != null) getCidadeDAO().salvar(cidades);
                chamaMenuPrincipal();
                break;
            case 1: //Segmento
                Segmento segmento= chamaCadastroSegmento();
                if (segmento != null) getSegmentoDAO().salvar(segmento);
                chamaMenuPrincipal();
                break;
            case 2: //Tipo de contato
                TipoContato tipoContato= chamaCadastroTipoContato();
                if (tipoContato != null) getTipoContatoDAO().salvar(tipoContato);
                chamaMenuPrincipal();
                break;
            case 3: //Startups
                Startups startups= chamaCadastroStartup();
                if (startups != null) getStartupDAO().salvar(startups);
                chamaMenuPrincipal();
                break;
            case 4: //Contatos
                Contato contato= chamaCadastroContato();
                if (contato != null) getContatoDAO().salvar(contato);
                chamaMenuPrincipal();
                break;
            case 5:
                chamaMenuRelatorios();
                break;
            case 6: // SAIR
                System.exit(0);
                break;
        }
    }

    private static Startups chamaCadastroStartup() throws SQLException, ClassNotFoundException {
        String[] opcao = {"Inserção", "Alteração", "Exclusão", "Voltar"};
        int opcaoCrud = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Startup app",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
        Startups startups= null;

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
                chamaMenuPrincipal();
                break;
        }
        return startups;
    }

    public static Cidade chamaCadastroCidades() throws SQLException, ClassNotFoundException {
        String[] opcao = {"Inserção", "Alteração", "Exclusão", "Voltar"};
        int opcaoCrud = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Startup app",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);

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
                chamaMenuPrincipal();
                break;
        }
        return cidade;
    }

    private static Segmento chamaCadastroSegmento() throws SQLException, ClassNotFoundException {
        String[] opcao = {"Inserção", "Alteração", "Exclusão", "Voltar"};
        int opcaoCrud = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Startup app",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
        Segmento segmento= null;

        switch (opcaoCrud) {
            case 0: //Inserção
                segmento = cadastraSegmento();
                break;

            case 1: //Alteração
                segmento = selecionaSegmento();
                segmento = editaSegmento(segmento);
                break;

            case 2: //Exclusão
                segmento = selecionaSegmento();
                getSegmentoDAO().remover(segmento);
                segmento = null;
                break;

            default: //Voltar
                chamaMenuPrincipal();
                break;
        }
        return segmento;
    }

    public static Contato chamaCadastroContato() throws SQLException, ClassNotFoundException {
        String[] opcao = {"Inserção", "Alteração", "Exclusão", "Voltar"};
        int opcaoCrud = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Startup app",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);

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
                chamaMenuPrincipal();
                break;

        }
        return contato;
    }

    public static TipoContato chamaCadastroTipoContato() throws SQLException, ClassNotFoundException {
        String[] opcao = {"Inserção", "Alteração", "Exclusão", "Voltar"};
        int opcaoCrud = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Startup app",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);

        TipoContato tipoContato = null;

        switch (opcaoCrud) {
            case 0: //Inserção
                tipoContato = cadastraTipoContato();
                break;

            case 1: //Alteração
                tipoContato = selecionaTipoContato();
                tipoContato = editaTipoContato(tipoContato);
                break;

            case 2: //Exclusão
                tipoContato = selecionaTipoContato();
                getTipoContatoDAO().remover(tipoContato);
                tipoContato = null;
                break;

            default: //Voltar
                chamaMenuPrincipal();
                break;
        }
        return tipoContato;
    }


    private static Cidade cadastraCidade(){
        Cidade cidades = new Cidade();

        JTextField nomeCidade = new JTextField();

        DefaultComboBoxModel<Estados> estadoEnum = new DefaultComboBoxModel<>(Estados.values());
        JComboBox<Estados> comboBoxEstado = new JComboBox<>(estadoEnum);

        Object[] message = {
                "Nome da cidade: ", nomeCidade,
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

    private static TipoContato cadastraTipoContato(){

        TipoContato tipoContato = new TipoContato();
        JTextField nomeTipoContato = new JTextField();

        Object[] message = {
                "Nome do tipo de contato: ", nomeTipoContato};

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de tipo de contatos", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeTipoContato.getText();
        tipoContato.setNome(nome);
        return tipoContato;

    }

    private static Startups cadastraStartup() throws SQLException, ClassNotFoundException {
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

        SegmentoDAO segmentoDAO = getSegmentoDAO();
        List<Segmento> listaSegmentos = segmentoDAO.buscarTodos();
        String[] nomesSegmentos = new String[listaSegmentos.size()];

        for(int i = 0; i < listaCidades.size(); i++){
            nomesCidades[i] = listaCidades.get(i).getNomeCidade();
        }

        for(int p = 0; p < listaSegmentos.size(); p++){
            nomesSegmentos[p] = listaSegmentos.get(p).getNome();
        }

        JComboBox<String> cidade = new JComboBox<>(nomesCidades);
        JComboBox<String> segmento = new JComboBox<>(nomesSegmentos);

        Object[] message = {
                "Nome da Startup: ", nomeStartup,
                "Descricao da Startup: ", descStartup,
                "Data de Criação da Startup (dd/mm/yyyy): ", dataInicio,
                "Descrição das Soluções: ", descSolucoes,
                "Rua: ", rua,
                "Bairro: ", bairro,
                "Cidade: ", cidade,
                "Segmento: ", segmento

        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Startups", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.CLOSED_OPTION) {
            chamaMenuPrincipal();
        }
        else{
            int codigoCidade = listaCidades.get(cidade.getSelectedIndex()).getId().intValue();
            int codigoSegmento = listaSegmentos.get(segmento.getSelectedIndex()).getId().intValue();
            startups.setNomeStartup(nomeStartup.getText());
            startups.setDescStartup(descStartup.getText());
            LocalDate data = LocalDate.parse(dataInicio.getText(), formatter);
            startups.setDataInicio(data);
            startups.setCodigoCidade(codigoCidade);
            startups.setEnderecoStartup(rua.getText() + " - " + bairro.getText());
            startups.setDescSolucoes(descSolucoes.getText());
            startups.setIdSegmento(codigoSegmento);
        }

        return startups;
    }

    private static Contato cadastraContato(){
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

    public static CidadeDAO getCidadeDAO(){
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO;
    }

    public static SegmentoDAO getSegmentoDAO(){
        SegmentoDAO segmentoDAO = new SegmentoDAO();
        return segmentoDAO;
    }

    public static TipoContatoDAO getTipoContatoDAO(){
        TipoContatoDAO tipoContatoDAO = new TipoContatoDAO();
        return tipoContatoDAO;
    }

    public static StartupDAO getStartupDAO(){
        StartupDAO startupDAO = new StartupDAO();
        return startupDAO;
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
    public static Segmento selecionaSegmento() throws SQLException {
        Object[] selectionValues = new Object[0];
        selectionValues = getSegmentoDAO().findSegmentosInArray();

        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione a Startup", "Startup APP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Segmento> segmento = getSegmentoDAO().buscarPorNome((String) selection);

        return segmento.get(0);
    }

    public static TipoContato selecionaTipoContato() throws SQLException, ClassNotFoundException {
        Object[] selectionValues = new Object[0];
        selectionValues = getTipoContatoDAO().findTipoContatoInArray();

        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o tipo de contato", "Startup APP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<TipoContato> tipoContatos = getTipoContatoDAO().buscarPorNome((String) selection);

        return tipoContatos.get(0);
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


    public static Contato selecionaContato(){
        Object[] selectionValues = new Object[0];
        selectionValues = getContatoDAO().findContatosInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o contato", "Startup APP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Contato> contatoList = getContatoDAO().buscarPorNome((String) selection);

        return contatoList.get(0);
    }

    private static Cidade editaCidade(Cidade cidade){
        Cidade cidades = new Cidade();

        JTextField nomeCidade = new JTextField();

        DefaultComboBoxModel<Estados> estadoEnum = new DefaultComboBoxModel<>(Estados.values());
        JComboBox<Estados> comboBoxEstado = new JComboBox<>(estadoEnum);

        Object[] message = {
                "Nome anterior: " + cidade.getNomeCidade(),
                "UF anterior: " + cidade.getEstados().getUF(),
                "Nome de Cidade: ", nomeCidade,
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


    private static Segmento editaSegmento(Segmento segmento){
        Segmento segmentos = new Segmento();

        JTextField nomeSegmento = new JTextField();

        Object[] message = {
                "Nome anterior: " + segmento.getNome(),
                "Nome de Segmento: ", nomeSegmento
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Segmentos", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeSegmento.getText();
        segmentos.setId(segmento.getId());
        segmentos.setNome(nome);

        return segmentos;

    }

    private static TipoContato editaTipoContato(TipoContato tipoContato){
        TipoContato tipoContatos= new TipoContato();
        JTextField nomeTipoContato = new JTextField();

        Object[] message = {
                "Nome anterior: " + tipoContato.getNome(),
                "Nome do tipo de contato: ", nomeTipoContato
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de tipo de contatos", JOptionPane.OK_CANCEL_OPTION);

        String nome = nomeTipoContato.getText();
        tipoContatos.setId(tipoContato.getId());
        tipoContatos.setNome(nome);

        return tipoContatos;

    }

    private static Startups editaStartup(Startups startup) throws SQLException, ClassNotFoundException {
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

        SegmentoDAO segmentoDAO = getSegmentoDAO();
        List<Segmento> listaSegmentos = segmentoDAO.buscarTodos();
        String[] nomesSegmentos = new String[listaSegmentos.size()];

        for(int i = 0; i < listaCidades.size(); i++){
            nomesCidades[i] = listaCidades.get(i).getNomeCidade();
        }

        for(int p = 0; p < listaSegmentos.size(); p++){
            nomesSegmentos[p] = listaSegmentos.get(p).getNome();
        }

        JComboBox<String> cidade = new JComboBox<>(nomesCidades);
        JComboBox<String> segmento = new JComboBox<>(nomesSegmentos);

        Object[] message = {
                "Nome anterior: ", startup.getNomeStartup(),
                "Nome da Startup: ", nomeStartup,
                "Descrição da Startup: ", descStartup,
                "Data de criação da Startup (dd/mm/yyyy): ", dataInicio,
                "Descrição das Soluções: ", descSolucoes,
                "Rua: ", rua,
                "Bairro: ", bairro,
                "Cidade: ", cidade,
                "Segmento: ", segmento
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Cadastro de Startups", JOptionPane.OK_CANCEL_OPTION);

        int codigoCidade = listaCidades.get(cidade.getSelectedIndex()).getId().intValue();
        int codigoSegmento = listaSegmentos.get(segmento.getSelectedIndex()).getId().intValue();

        startups.setId(startup.getId());
        startups.setNomeStartup(nomeStartup.getText());
        startups.setDescStartup(descStartup.getText());
        LocalDate data = LocalDate.parse(dataInicio.getText(), formatter);
        startups.setDataInicio(data);
        startups.setCodigoCidade(codigoCidade);
        startups.setEnderecoStartup(rua.getText() + " - " + bairro.getText());
        startups.setDescSolucoes(descSolucoes.getText());
        startups.setIdSegmento(codigoSegmento);

        return startups;
    }

    private static Contato editaContato(Contato contato){
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

    private static void chamaRelatorioStartups() throws SQLException, ClassNotFoundException {
        List<Startups> startups = getStartupDAO().buscarTodos();
        RelatorioStartupsForm.emitirRelatorio(startups);
    }

    public static void chamaMenuRelatorios() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuProcesso = {"Cidades", "Startups", "Voltar"};
        int menu_processos = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Relatórios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProcesso, opcoesMenuProcesso[0]);

        switch (menu_processos) {
            case 0: //Cidades
                RelatorioCidadeForm.emitirRelatorio(getCidadeDAO().buscarTodos());
                break;
            case 1: //Startups
                chamaRelatorioStartups();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

}