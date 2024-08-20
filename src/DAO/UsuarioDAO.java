package DAO;

import Conectar.ConectarBanco;
import Mode.Usuarios;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    // Método para adicionar um novo usuário
    public void adicionarUsuario(Usuarios usuario) throws SQLException {
        // Verifica permissões antes de adicionar usuário
        if (!isAdmin()) {
            System.out.println("Apenas administradores podem adicionar usuários.");
            return;
        }

        String sql = "INSERT INTO usuarios (usuario, senha, tipo, ativado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConectarBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTipo());
            stmt.setBoolean(4, usuario.isAtivado());

            stmt.executeUpdate(); // Executa o comando SQL para inserir o usuário
        }
    }

    // Método para listar todos os usuários
    public ArrayList<Usuarios> listaUsuarios() throws SQLException {
        // Verifica permissões antes de listar usuários
        if (!isAdmin()) {
            System.out.println("Apenas administradores podem listar usuários.");
            return new ArrayList<>(); // Retorna lista vazia se não for administrador
        }

        ArrayList<Usuarios> usuarios = new ArrayList<>();

        // Conectar ao banco de dados
        try (Connection conn = ConectarBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Criar um objeto Usuarios para cada linha retornada do banco de dados
                Usuarios usuario = new Usuarios();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setAtivado(rs.getBoolean("ativado"));

                // Adicionar o usuário à lista
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    // Método para excluir um usuário pelo seu ID
    public void excluirUsuario(int id) throws SQLException {
        // Verifica permissões antes de excluir usuário
        if (!isAdmin()) {
            System.out.println("Apenas administradores podem excluir usuários.");
            return;
        }

        String sql = "DELETE FROM usuarios WHERE idusuario = ?";
        try (Connection conn = ConectarBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um usuário existente
    public void atualizarUsuario(Usuarios usuario) throws SQLException {
        // Verifica permissões antes de atualizar usuário
        if (!isAdmin()) {
            System.out.println("Apenas administradores podem atualizar usuários.");
            return;
        }

        String sql = "UPDATE usuarios SET usuario = ?, senha = ?, tipo = ?, ativado = ? WHERE idusuario = ?";

        try (Connection conn = ConectarBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTipo());
            stmt.setBoolean(4, usuario.isAtivado());
            stmt.setInt(5, usuario.getIdusuario());

            stmt.executeUpdate(); // Executa o comando SQL para atualizar o usuário
        }
    }

    // Método para validar um usuário usando PreparedStatement de forma segura
    public static Usuarios validarUsuarioSeguro(Usuarios usuario) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ? AND ativado = true";
        Usuarios usuarioEncontrado = null;

        try (Connection conn = ConectarBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuarios();
                    usuarioEncontrado.setIdusuario(rs.getInt("idusuario"));
                    usuarioEncontrado.setUsuario(rs.getString("usuario"));
                    usuarioEncontrado.setSenha(rs.getString("senha"));
                    usuarioEncontrado.setTipo(rs.getString("tipo"));
                    usuarioEncontrado.setAtivado(rs.getBoolean("ativado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioEncontrado;
    }

    // Método para verificar se o usuário logado é administrador
    private boolean isAdmin() {
        // Substitua pela lógica real de obtenção do tipo de usuário logado
        String tipoUsuarioLogado = getTipoUsuarioLogado();

        return "administrador".equalsIgnoreCase(tipoUsuarioLogado);
    }

    // Método fictício para obter o tipo de usuário logado
    private String getTipoUsuarioLogado() {
        // Aqui, estamos retornando "administrador" como exemplo
        return "administrador";
    }
}
