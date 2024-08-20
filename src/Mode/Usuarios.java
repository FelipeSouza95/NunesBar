package Mode;

public class Usuarios {

    private int idusuario;
    private String usuario;
    private String senha;
    private String tipo;
    private boolean ativado;

    // Construtor padrão vazio
    public Usuarios() {
    }

    // Construtor com parâmetros, exceto o idusuario
    public Usuarios(String usuario, String senha, String tipo, boolean ativado) {
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
        this.ativado = ativado;
    }

    // Construtor com todos os parâmetros
    public Usuarios(int idusuario, String usuario, String senha, String tipo, boolean ativado) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
        this.ativado = ativado;
    }

    // Métodos getters e setters para todos os campos
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

}
