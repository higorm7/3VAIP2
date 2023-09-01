package exceptions;

public class NomeDeGrupoJaCadastradoException extends Exception {

    private String nome;

    public NomeDeGrupoJaCadastradoException(String nome) {
        super("O nome " + nome + " já está cadastrado em outro grupo");
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
