package Mode;

import DAO.ProdutoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaProdutos {

    private ArrayList<Produtos> listaProdutos; // Lista para armazenar os produtos localmente
    private ProdutoDAO produtoDAO; // Objeto DAO para interação com o banco de dados

    public ListaProdutos() {
        this.listaProdutos = new ArrayList<>(); // Inicializa a lista local de produtos
        this.produtoDAO = new ProdutoDAO(); // Inicializa o DAO para operações de banco de dados

        // Tenta carregar os produtos do banco de dados
        try {
            listaProdutos.addAll(produtoDAO.listarProdutos()); // Adiciona todos os produtos do banco para a lista local
        } catch (SQLException e) {
            System.err.println("Erro ao carregar produtos do banco de dados: " + e.getMessage());
            // Trata o erro de forma adequada, por exemplo, inicializando com uma lista vazia
            listaProdutos = new ArrayList<>(); // Inicializa com uma lista vazia caso falhe o carregamento
        }
    }

    // Método para adicionar um produto na lista e no banco de dados
    public void adicionarProduto(Produtos produto) {
        try {
            produtoDAO.cadastrarProduto(produto); // Adiciona no banco de dados
            listaProdutos.add(produto); // Adiciona na lista local apenas se a adição no banco for bem-sucedida
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto no banco de dados: " + e.getMessage());
        }
    }

    // Método para remover um produto da lista e do banco de dados
    public void excluirProduto(int id) {
        try {
            produtoDAO.deletarProduto(id); // Remove do banco de dados
            listaProdutos.removeIf(produto -> produto.getIdproduto() == id); // Remove da lista local se remoção do banco for bem-sucedida
        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto do banco de dados: " + e.getMessage());
        }
    }

    // Método para atualizar um produto na lista e no banco de dados
    public void atualizarProduto(Produtos produto) {
        try {
            produtoDAO.atualizarProduto(produto); // Atualiza no banco de dados

            // Atualiza na lista local
            for (int i = 0; i < listaProdutos.size(); i++) {
                if (listaProdutos.get(i).getIdproduto() == produto.getIdproduto()) {
                    listaProdutos.set(i, produto);
                    break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto no banco de dados: " + e.getMessage());
        }
    }

    // Método para obter a lista de produtos
    public ArrayList<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    // Método para listar produtos por nome, categoria ou ambos utilizando o DAO
    public ArrayList<Produtos> listarProdutosPorNomeOuCategoria(String nome, String categoria) throws SQLException {
        return produtoDAO.listarProdutosPorNomeOuCategoria(nome, categoria);
    }

    // Método para buscar um produto pelo ID utilizando o DAO
    public Produtos buscarProduto(int idProduto) throws SQLException {
        return produtoDAO.buscarProduto(idProduto);
    }

    // Método para verificar se um produto está na caixa utilizando o DAO
    public boolean verificarProdutoNaCaixa(int idProduto) {
        return produtoDAO.verificarProdutoNaCaixa(idProduto);
    }

    // Método para atualizar a quantidade de um produto pelo ID utilizando o DAO
    public void atualizarQuantidadeProduto(int id, int novaQuantidade) throws SQLException {
        produtoDAO.atualizarQuantidadeProduto(id, novaQuantidade);
    }
}
