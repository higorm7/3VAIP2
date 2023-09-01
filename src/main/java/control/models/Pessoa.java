package control.models;

import java.util.List;

public class Pessoa {

    private String nome;
    private String apelido;
    private String senha;
    private List<Presente> presentesDesejados;

    public Pessoa(String nome, String apelido, String senha) {
        this.nome = nome;
        this.apelido = apelido;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getSenha() {
        return senha;
    }

    public List<Presente> getPresentesDesejados() {
        return presentesDesejados;
    }

    @Override
    public String toString() {
        return "Nome:      " + this.nome +
                "\nApelido:   " + this.apelido;
    }

}
