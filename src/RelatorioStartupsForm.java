import Relatorio.RelatorioStartups;
import model.Startups;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RelatorioStartupsForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Código", "Nome", "Descrição", "Data Inicio", "Rua - Bairro", "Descrição das Soluções", "Cidade", ""};

    protected JTable table;
    protected JScrollPane scroller;

    protected RelatorioStartups tabela;

    public RelatorioStartupsForm(Vector<Startups> vetorDados){
        iniciarComponentes(vetorDados);
    }

    public void iniciarComponentes(Vector<Startups> vetorDados) {
        tabela = new RelatorioStartups(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioStartups.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio(List<Startups> startups) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatorio");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    frame.setVisible(false);
                    try {
                        Main.chamaMenuRelatorios();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Vector<Startups> vetorDados = new Vector<Startups>();
            for (Startups manutencao : startups) {
                vetorDados.add(manutencao);
            }


            frame.getContentPane().add(new RelatorioStartupsForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
