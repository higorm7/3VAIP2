package exceptions;

import control.models.Grupo;

public class GrupoNaoContemPessoasSuficientesException extends Exception {

    private Grupo grupo;

    public GrupoNaoContemPessoasSuficientesException(Grupo grupo) {
        super("O grupo " + grupo.getNome() + " não possui pessoas suficientes");
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

}
