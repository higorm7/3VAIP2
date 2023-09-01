package exceptions;

public class ApelidoJaCadastradoException extends Exception {

    private String apelido;

    public ApelidoJaCadastradoException(String apelido) {
        super("O apelido " + apelido + " já está cadastrado.");
        this.apelido = apelido;
    }

    public String getApelido() {
        return this.apelido;
    }

}
