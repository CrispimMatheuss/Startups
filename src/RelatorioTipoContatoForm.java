
import Relatorio.TableTipoContato;
import model.TipoContato;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RelatorioTipoContatoForm extends JPanel {
        private static final long serialVersionUID = 1L;

        public static final String[] nomeColunas =
                {"Código", "Nome", "Startup", "Tipo contato", ""};

        protected JTable table;
        protected JScrollPane scroller;
        protected TableTipoContato tabela;

        public RelatorioTipoContatoForm(Vector<TipoContato> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<TipoContato> vetorDados) {
            tabela = new TableTipoContato(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new javax.swing.JScrollPane(table);
            table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

            TableColumn colunaEscondida = table.getColumnModel().getColumn(TableTipoContato.INDEX_ESCONDIDO);
            colunaEscondida.setMinWidth(2);
            colunaEscondida.setPreferredWidth(2);
            colunaEscondida.setMaxWidth(2);
            setLayout(new BorderLayout());
            add(scroller, BorderLayout.CENTER);
        }

        public static void emitirRelatorio(List<TipoContato> tipoContato) {
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
                Vector<TipoContato> vetorDados = new Vector<TipoContato>();
                for (TipoContato tipoContatos : tipoContato) {
                    vetorDados.add(tipoContatos);
                }

                frame.getContentPane().add(new RelatorioTipoContatoForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}


