package exceptions;

import control.models.Grupo;

public class DataDeSorteioFuturaException extends Exception {

    private Grupo grupo;

    public DataDeSorteioFuturaException(Grupo grupo) {
        super("A data de sorteio do grupo é " + grupo.getDataSorteio());
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

}
