package Relatorio;

import model.Contato;
import model.Startups;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableStartup extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        public static final int INDEX_CODIGO = 0;
        public static final int INDEX_NOME = 1;
        public static final int INDEX_IDSTARTUP = 2;
        public static final int INDEX_IDTIPOCONTATO = 3;
        public static final int INDEX_ESCONDIDO = 4;

        protected String[] nomeColunas;
        protected Vector<Startups> vetorDados;

        public TableStartup(String[] columnNames, Vector<Startups> vetorDados) {
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
            Startups registroStartups = vetorDados.get(linha);
            switch (coluna) {
                case INDEX_CODIGO:
                    return registroStartups.getId();
                case INDEX_NOME:
                    return registroStartups.getNome();
                case INDEX_IDSTARTUP:
                    return registroStartups.getIdStartup();
                case INDEX_IDTIPOCONTATO:
                    return registroStartups.getIdTipoContato();
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


}
