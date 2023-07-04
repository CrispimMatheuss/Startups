import model.*;
import model.Usuario;
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
        String[] opcoesMenuCadastro = {"Startups", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //Startups
                Startups startups = chamaCadastroStartup();
//                if (startups != null) getStartupDAO().salvar(startups);
                chamaMenuCadastros();
                break;
            case 1: //Seguradoras
                /*Seguradora seguradora = chamaCadastroSeguradora();
                if (seguradora != null) getSeguradoraDAO().salvar(seguradora);*/
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

    private static Startups cadastraStartup(){
        JPopupMenu jPopupMenu;
        JButton button;
        Startups startups = new Startups();

        JTextField nomeStartup = new JTextField();
        JTextField descStartup = new JTextField();
        JTextField dataInicio  = new JTextField();

        //Trazer as cidades
        JComboBox<String> cidade = new JComboBox<>();
        cidade.setModel(new DefaultComboBoxModel<>(getCidades().toArray(new String[0])));

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

    private static List<String> getCidades() {
        List<String> cidades = new ArrayList<>();
        cidades.add("Criciúma");
        cidades.add("Içara");
        // Adicione mais cidades se necessário
        return cidades;
    }

}