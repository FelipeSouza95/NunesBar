package DAO;

import Conectar.ConectarBanco;
import Mode.Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {

    // Método para listar todos os produtos
    public ArrayList<Produtos> listarProdutos() throws SQLException {
        ArrayList<Produtos> listaProdutos = new ArrayList<>();
        String sql = "SELECT idproduto, categoria, nome, valor, quantidade FROM produtos";

        try (Connection conexao = ConectarBanco.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idproduto");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");

                Produtos produto = new Produtos(id, nome, categoria, valor, quantidade);
                listaProdutos.add(produto);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar produtos: " + e.getMessage());
        }

        return listaProdutos;
    }

    // Método para adicionar um novo produto
    public void cadastrarProduto(Produtos produto) throws SQLException {
        String sql = "INSERT INTO produtos (categoria, nome, valor, quantidade) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConectarBanco.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getValor());
            stmt.setInt(4, produto.getQuantidade());

            stmt.executeUpdate();

            // Obtém o ID gerado automaticamente e define no objeto produto
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idProduto = rs.getInt(1);
                    produto.setIdproduto(idProduto); // Define o ID gerado no objeto produto
                }
            }
        }
    }

    // Método para excluir um produto pelo ID
    public void deletarProduto(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE idproduto = ?";

        try (Connection conexao = ConectarBanco.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir produto", e);
        }
    }

    // Método para atualizar a quantidade de um produto pelo ID
    public void atualizarQuantidadeProduto(int id, int novaQuantidade) throws SQLException {
        String sql = "UPDATE produtos SET quantidade = ? WHERE idproduto = ?";

        try (Connection conexao = ConectarBanco.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar quantidade do produto: " + e.getMessage());
        }
    }

    // Método para listar produtos por nome, categoria ou ambos
    public ArrayList<Produtos> listarProdutosPorNomeOuCategoria(String nome, String categoria) throws SQLException {
        ArrayList<Produtos> listaProdutos = new ArrayList<>();
        String sql = "SELECT idproduto, categoria, nome, valor, quantidade FROM produtos WHERE nome LIKE ? OR categoria LIKE ?";

        try (Connection conexao = ConectarBanco.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + categoria + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idproduto");
                String nomeProduto = rs.getString("nome");
                String categoriaProduto = rs.getString("categoria");
                double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");

                Produtos produto = new Produtos(id, nomeProduto, categoriaProduto, valor, quantidade);
                listaProdutos.add(produto);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar produtos por nome ou categoria: " + e.getMessage());
        }

        return listaProdutos;
    }

    // Método para buscar um produto pelo ID
    public Produtos buscarProduto(int idProduto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produtos produto = null;

        try {
            conn = ConectarBanco.getConnection();
            String sql = "SELECT categoria, nome, valor, quantidade FROM produtos WHERE idProduto=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);

            rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");

                produto = new Produtos(idProduto, nome, categoria, valor, quantidade);
            } else {
                System.out.println("Produto não encontrado com o ID: " + idProduto);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar produto: " + e.getMessage());
        } finally {
            ConectarBanco.closeConnection(conn, stmt, rs);
        }

        return produto;
    }

    // Método para verificar se um produto está na caixa
    public boolean verificarProdutoNaCaixa(int idProduto) {
        String sql = "SELECT idproduto FROM caixa WHERE idproduto = ?";
        boolean produtoNaCaixa = false;

        try (Connection conn = ConectarBanco.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produtoNaCaixa = true; // Produto encontrado na caixa
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar produto na caixa.", e);
        }

        return produtoNaCaixa;
    }

    // Método para atualizar um produto
    public void atualizarProduto(Produtos produto) throws SQLException {
        String sql = "UPDATE produtos SET categoria = ?, nome = ?, valor = ?, quantidade = ? WHERE idproduto = ?";

        try (Connection conexao = ConectarBanco.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setDouble(3, produto.getValor());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getIdproduto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar produto: " + e.getMessage());
        }
    }
}
