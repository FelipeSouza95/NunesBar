package DAO;

import Conectar.ConectarBanco;
import Mode.Relatorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    // Método para inserir um novo relatório na tabela
    public void inserirRelatorio(Relatorios relatorio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConectarBanco.getConnection();
            String sql = "INSERT INTO relatorios (nota, valor, data) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, relatorio.getNota());
            stmt.setDouble(2, relatorio.getValor());
            stmt.setDate(3, new java.sql.Date(relatorio.getData().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir relatório: " + e.getMessage());
        } finally {
            try {
                ConectarBanco.closeConnection(conn, stmt, null);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para buscar um relatório pelo ID
    public Relatorios buscarPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Relatorios relatorio = null;
        try {
            conn = ConectarBanco.getConnection();
            String sql = "SELECT * FROM relatorios WHERE idrelatorio = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                relatorio = new Relatorios(
                        rs.getString("nota"),
                        rs.getDouble("valor"),
                        rs.getDate("data")
                );
                relatorio.setIdrelatorio(rs.getInt("idrelatorio"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar relatório por ID: " + e.getMessage());
        } finally {
            try {
                ConectarBanco.closeConnection(conn, stmt, rs);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return relatorio;
    }

    // Método para atualizar um relatório na tabela
    public void atualizarRelatorio(Relatorios relatorio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConectarBanco.getConnection();
            String sql = "UPDATE relatorios SET nota = ?, tipo = ?, valor = ?, data = ? WHERE idrelatorio = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, relatorio.getNota());
            stmt.setDouble(2, relatorio.getValor());
            stmt.setDate(3, new java.sql.Date(relatorio.getData().getTime()));
            stmt.setInt(4, relatorio.getIdrelatorio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar relatório: " + e.getMessage());
        } finally {
            try {
                ConectarBanco.closeConnection(conn, stmt, null);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para deletar um relatório da tabela
    public void deletarRelatorio(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConectarBanco.getConnection();
            String sql = "DELETE FROM relatorios WHERE idrelatorio = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar relatório: " + e.getMessage());
        } finally {
            try {
                ConectarBanco.closeConnection(conn, stmt, null);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // Método para listar todos os relatórios da tabela
    public List<Relatorios> listarRelatorios() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Relatorios> relatorios = new ArrayList<>();
        try {
            conn = ConectarBanco.getConnection();
            String sql = "SELECT * FROM relatorios";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Relatorios relatorio = new Relatorios(
                        rs.getString("nota"),
                        rs.getDouble("valor"),
                        rs.getDate("data")
                );
                relatorio.setIdrelatorio(rs.getInt("idrelatorio"));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar relatórios: " + e.getMessage());
        } finally {
            try {
                ConectarBanco.closeConnection(conn, stmt, rs);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return relatorios;
    }
}
