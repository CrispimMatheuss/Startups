package Relatorio;

import model.Startups;
import repository.CidadeDAO;
import repository.SegmentoDAO;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioStartups extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    public static final int INDEX_ID_STARTUP = 0;
    public static final int INDEX_NOME_STARTUP = 1;
    public static final int INDEX_DESCRICAO = 2;
    public static final int INDEX_DATA_INICIO = 3;
    public static final int INDEX_ENDERECO = 4;
    public static final int INDEX_DESCRICAO_SOLUCOES = 5;
    public static final int INDEX_SEGMENTO = 6;
    public static final int INDEX_CIDADE = 7;
    public static final int INDEX_ESCONDIDO = 8;

    protected String[] nomeColunas;
    protected Vector<Startups> vetorDados;

    public RelatorioStartups(String[] columnNames, Vector<Startups> vetorDados){
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

    public Object getValueAt(int linha, int coluna){
        Startups startups = (Startups) vetorDados.get(linha);
        SegmentoDAO segmentoDAO = new SegmentoDAO();
        CidadeDAO cidadeDAO = new CidadeDAO();
        switch (coluna){
            case INDEX_ID_STARTUP:
                return startups.getId();
            case INDEX_NOME_STARTUP:
                return startups.getNomeStartup();
            case INDEX_DESCRICAO:
                return startups.getDescStartup();
            case INDEX_DATA_INICIO:
                return startups.getDataInicio();
            case INDEX_ENDERECO:
                return startups.getEnderecoStartup();
            case INDEX_DESCRICAO_SOLUCOES:
                return startups.getDescSolucoes();
            case INDEX_SEGMENTO:
                return segmentoDAO.buscarPorCodigo(startups.getIdSegmento().intValue());
            case INDEX_CIDADE:
                return cidadeDAO.buscarPorCodigo(startups.getCodigoCidade().intValue());
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
