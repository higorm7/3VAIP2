package control.models;

public class Presente {

    private String categoria;
    private String descricao;
    private double preco;

    public Presente(String categoria, String descricao, double preco) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }
    @Override
    public String toString() {
        return "Descrição: " + this.getDescricao() + "\nCategoria: " + getCategoria() + "\nPreço: " + this.getPreco();
    }
}
