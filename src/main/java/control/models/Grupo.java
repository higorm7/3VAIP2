package control.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private String nome;
    private LocalDate dataSorteio;
    private List<Pessoa> participantes;
    private List<AmigosSecretos> amigosSecretos;

    public Grupo(String nome, LocalDate dataSorteio) {
        this.nome = nome;
        this.dataSorteio = dataSorteio;
        this.participantes = new ArrayList<>(0);
        this.amigosSecretos = new ArrayList<>(0);
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataSorteio() {
        return dataSorteio;
    }

    public List<Pessoa> getParticipantes() {
        return participantes;
    }

    public List<AmigosSecretos> getAmigosSecretos() {
        return amigosSecretos;
    }

    public void setAmigosSecretos(List<AmigosSecretos> amigosSecretos) {
        this.amigosSecretos = amigosSecretos;
    }

    @Override
    public String toString() {
        String value = "Nome do grupo: " + this.getNome() + "\nData do sorteio: " + this.getDataSorteio() +
                "\nParticipantes: \n";
        for (Pessoa pessoa : this.getParticipantes()) {
            value += pessoa.getApelido() + "\n";
        }

        return value;
    }

}
