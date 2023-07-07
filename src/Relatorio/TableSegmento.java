package Relatorio;

import model.Segmento;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableSegmento extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        public static final int INDEX_CODIGO = 0;
        public static final int INDEX_NOME = 1;
        public static final int INDEX_ESCONDIDO = 2;

        protected String[] nomeColunas;
        protected Vector<Segmento> vetorDados;

        public TableSegmento(String[] columnNames, Vector<Segmento> vetorDados) {
            this.nomeColunas = columnNames;
            this.vetorDados = vetorDados;
        }

        @Override
        public String getColumnName(int column) {
            return nomeColunas[column];
        }

        @Override
        public boolean isCellEditable(int linha, int coluna) {
            if (coluna == INDEX_ESCONDIDO) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            Segmento registroSegmento = (Segmento) vetorDados.get(linha);
            switch (coluna) {
                case INDEX_CODIGO:
                    return registroSegmento.getId();
                case INDEX_NOME:
                    return registroSegmento.getNome();
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


