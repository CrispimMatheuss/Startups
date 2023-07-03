import model.*;
import model.Usuario;
import repository.UsuarioDAO;

import javax.swing.*;
import java.sql.SQLException;

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
                //chamaMenuCadastros();
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
        String[] opcoesMenuCadastro = {"Pessoa", "Seguradora", "Seguro", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //Startus
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
//                startups = cadastraStartup();
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
        Startups startups = new Startups();
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da Startup: ");

        return startups;
    }

}