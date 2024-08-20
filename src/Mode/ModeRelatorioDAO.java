
package Mode;

import java.util.ArrayList;


public interface ModeRelatorioDAO {
    // Métodos CRUD
    
    // Cria um novo relatório
    public void criarRelatorio(Relatorios relatorio);
    
    // Obtém um relatório pelo seu ID
    public Relatorios obterRelatorioPorId(int id);
    
    // Atualiza os dados de um relatório existente
    public void atualizarRelatorio(Relatorios relatorio);
    
    // Exclui um relatório pelo seu ID
    public void excluirRelatorio(int id);
    
    // Obtém todos os relatórios
    public ArrayList<Relatorios> listarRelatorios();
}
