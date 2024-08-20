package Mode;

public class Produtos {

    private int idproduto;
    private String nome;
    private String categoria;
    private double valor;
    private int quantidade;

    public Produtos() {
    }
    
    public Produtos(String nome, String categoria, double valor, int quantidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Produtos(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Produtos(int idproduto, String nome, double valor) {
        this.idproduto = idproduto;
        this.nome = nome;
        this.valor = valor;
    }
    
    public Produtos(String nome, double valor, int quantidade) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Produtos(int idproduto, String nome, double valor, int quantidade) {
        this.idproduto = idproduto;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Produtos(int idproduto, String categoria, String nome, double valor, int quantidade) {
        this.idproduto = idproduto;
        this.categoria = categoria;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
