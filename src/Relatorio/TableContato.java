package Relatorio;

import model.Contato;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableContato extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        public static final int INDEX_CODIGO = 0;
        public static final int INDEX_NOME = 1;
        public static final int INDEX_IDSTARTUP = 2;
        public static final int INDEX_IDTIPOCONTATO = 3;
        public static final int INDEX_ESCONDIDO = 4;

        protected String[] nomeColunas;
        protected Vector<Contato> vetorDados;

        public TableContato(String[] columnNames, Vector<Contato> vetorDados) {
            this.nomeColunas = columnNames;
            this.vetorDados = vetorDados;
        }

        @Override
        public String getColumnName(int column) {
            return nomeColunas[column];
        }

        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return coluna != INDEX_ESCONDIDO;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            Contato registroContato = vetorDados.get(linha);
            switch (coluna) {
                case INDEX_CODIGO:
                    return registroContato.getId();
                case INDEX_NOME:
                    return registroContato.getNome();
                case INDEX_IDSTARTUP:
                    return registroContato.getIdStartup();
                case INDEX_IDTIPOCONTATO:
                    return registroContato.getIdTipoContato();
                default:
                    return new Object();
            }
        }

        @Override
        public int getRowCount() {
            return vetorDados.size();
        }

        @Override
        public int getColumnCount() {
            return nomeColunas.length;
        }
    }
