package Mode;

import java.util.Date;

public class Relatorios {
    private int idrelatorio;
    private String nota;
    private double valor;
    private Date data;

    // Construtor que aceita os parâmetros necessários
    public Relatorios(String nota, double valor, Date data) {
        this.nota = nota;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters
    public int getIdrelatorio() {
        return idrelatorio;
    }

    public void setIdrelatorio(int idrelatorio) {
        this.idrelatorio = idrelatorio;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}