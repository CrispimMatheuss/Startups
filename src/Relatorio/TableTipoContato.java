package Relatorio;

import model.TipoContato;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableTipoContato extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        public static final int INDEX_CODIGO = 0;
        public static final int INDEX_NOME = 1;
        public static final int INDEX_ESCONDIDO = 2;

        protected String[] nomeColunas;
        protected Vector<TipoContato> vetorDados;

        public TableTipoContato(String[] columnNames, Vector<TipoContato> vetorDados) {
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
            TipoContato registroTipoContato = vetorDados.get(linha);
            switch (coluna) {
                case INDEX_CODIGO:
                    return registroTipoContato.getId();
                case INDEX_NOME:
                    return registroTipoContato.getNome();
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

