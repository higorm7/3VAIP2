package exceptions;

import control.models.Grupo;

public class GrupoNaoFoiSorteadoException extends Exception {

    private Grupo grupo;

    public GrupoNaoFoiSorteadoException(Grupo grupo) {
        super("O grupo " + grupo + " não foi sorteado");
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

}
