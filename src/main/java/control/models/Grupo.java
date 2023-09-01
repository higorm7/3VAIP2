package control.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private String nome;
    private LocalDate dataSorteio;
    private List<Pessoa> participantes;

    //adicionar lista de amigos secretos

    public Grupo(String nome, LocalDate dataSorteio) {
        this.nome = nome;
        this.dataSorteio = dataSorteio;
        this.participantes = new ArrayList<>(0);
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

    public void setParticipantes(List<Pessoa> participantes) {
        this.participantes = participantes;
    }

}
