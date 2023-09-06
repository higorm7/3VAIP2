package exceptions;

import control.models.Grupo;

public class GrupoJaSorteadoException extends Exception {

    private Grupo grupo;

    public GrupoJaSorteadoException(Grupo grupo) {
        super("O grupo " + grupo.getNome() + " já foi sorteado.");
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }
}
