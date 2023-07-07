import Relatorio.TableContato;
import model.Contato;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RelatorioContatoForm  extends JPanel {
        private static final long serialVersionUID = 1L;

        public static final String[] nomeColunas =
                {"Código", "Startup", "Tipo contato", "Contato", ""};

        protected JTable table;
        protected JScrollPane scroller;
        protected TableContato tabela;

        public RelatorioContatoForm(Vector<Contato> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<Contato> vetorDados) {
            tabela = new TableContato(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new javax.swing.JScrollPane(table);
            table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

            TableColumn colunaEscondida = table.getColumnModel().getColumn(TableContato.INDEX_ESCONDIDO);
            colunaEscondida.setMinWidth(2);
            colunaEscondida.setPreferredWidth(2);
            colunaEscondida.setMaxWidth(2);
            setLayout(new BorderLayout());
            add(scroller, BorderLayout.CENTER);
        }

        public static void emitirRelatorio(List<Contato> contato) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatório");

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
                Vector<Contato> vetorDados = new Vector<Contato>();
                for (Contato contatos : contato) {
                    vetorDados.add(contatos);
                }

                frame.getContentPane().add(new RelatorioContatoForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}


