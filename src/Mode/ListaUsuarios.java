package Mode;

import static Conectar.ConectarBanco.getConnection;
import DAO.UsuarioDAO;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListaUsuarios {

    private ArrayList<Usuarios> listaUsuarios;
    private UsuarioDAO usuarioDAO;

    public ArrayList<Usuarios> listaUsuarios() throws SQLException {
    ArrayList<Usuarios> usuarios = new ArrayList<>();
    String query = "SELECT idusuario, usuario, senha, tipo, ativado FROM Usuarios";
    
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            int idusuario = rs.getInt("idusuario");
            String usuario = rs.getString("usuario");
            String senha = rs.getString("senha");
            String tipo = rs.getString("tipo");
            boolean ativado = rs.getBoolean("ativado");
            
            Usuarios u = new Usuarios(idusuario, usuario, senha, tipo, ativado);
            usuarios.add(u);
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao listar usuários: " + ex.getMessage());
        throw ex;
    }
    
    return usuarios;
}

    public void adicionarUsuario(Usuarios usuario) {
        listaUsuarios.add(usuario);

        try {
            usuarioDAO.adicionarUsuario(usuario); // Chama o método adicionarUsuario do UsuarioDAO
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário no banco de dados: " + e.getMessage());
            listaUsuarios.remove(usuario);
        }
    }

    public void excluirUsuario(int id) {
        listaUsuarios.removeIf(usuario -> usuario.getIdusuario() == id);

        try {
            usuarioDAO.excluirUsuario(id); // Chama o método excluirUsuario do UsuarioDAO
        } catch (SQLException e) {
            System.err.println("Erro ao excluir o usuário do banco de dados: " + e.getMessage());
        }
    }

    public ArrayList<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }
}
