package control.models;

public class AmigosSecretos {

    private Pessoa presenteador;
    private Pessoa presenteado;

    public AmigosSecretos(Pessoa presenteador, Pessoa presenteado) {
        this.presenteador = presenteador;
        this.presenteado = presenteado;
    }

    public Pessoa getPresenteador() {
        return presenteador;
    }

    public Pessoa getPresenteado() {
        return presenteado;
    }

    @Override
    public String toString() {
        return "Presenteador: " + getPresenteador().getApelido() +
                "\nPresenteado: " + getPresenteado().getApelido() + '\n';
    }
}
