package Mode;

import DAO.RelatorioDAO;
import java.util.List;

public class ListaRelatorios {
    
    private RelatorioDAO relatorioDAO;

    public ListaRelatorios() {
        this.relatorioDAO = new RelatorioDAO();
    }

    // Método para adicionar um relatório (insere no banco de dados)
    public void adicionarRelatorio(String nota, double valor, java.util.Date data) {
        Relatorios relatorio = new Relatorios(nota, valor, data);
        relatorioDAO.inserirRelatorio(relatorio);
    }

    // Método para retornar todos os relatórios do banco de dados
    public List<Relatorios> listarRelatorios() {
        return relatorioDAO.listarRelatorios();
    }

    // Método para buscar um relatório pelo ID no banco de dados
    public Relatorios buscarPorId(int id) {
        return relatorioDAO.buscarPorId(id);
    }

    // Método para atualizar um relatório no banco de dados
    public void atualizarRelatorio(int id, String nota, double valor, java.util.Date data) {
        Relatorios relatorio = new Relatorios(nota, valor, data);
        relatorio.setIdrelatorio(id);
        relatorioDAO.atualizarRelatorio(relatorio);
    }

    // Método para deletar um relatório do banco de dados
    public void deletarRelatorio(int id) {
        relatorioDAO.deletarRelatorio(id);
    }
}
