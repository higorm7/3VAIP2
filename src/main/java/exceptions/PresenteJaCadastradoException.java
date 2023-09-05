package exceptions;

import control.models.Presente;

public class PresenteJaCadastradoException extends Exception {

    private Presente presente;

    public PresenteJaCadastradoException(Presente presente) {
        super("O presente de descrição: " + presente.getDescricao() + " já está cadastrado");
        this.presente = presente;
    }

    public Presente getPresente() {
        return presente;
    }

}
