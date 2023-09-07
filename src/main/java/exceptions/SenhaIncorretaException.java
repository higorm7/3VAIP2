package exceptions;

public class SenhaIncorretaException extends Exception {

    private String apelido;

    public SenhaIncorretaException(String apelido) {
        super("A senha da pessoa " + apelido + " está incorreta");
        this.apelido = apelido;
    }

    public String getApelido() {
        return apelido;
    }

}
