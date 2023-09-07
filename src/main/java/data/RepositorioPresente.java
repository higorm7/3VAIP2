package data;

import control.models.Pessoa;
import control.models.Presente;
import exceptions.PresenteJaCadastradoException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPresente implements IRepositorioPresente {

    private List<Presente> presentes;
    private static IRepositorioPresente instance;

    private RepositorioPresente() {
        this.presentes = new ArrayList<>(0);
        this.presentes.add(new Presente("carro", "carro", 1000));
        this.presentes.add(new Presente("brinquedo", "bola de fut", 10));
        this.presentes.add(new Presente("pix", "pix de 100", 100));
        this.presentes.add(new Presente("pinga", "pinga", 51));
    }

    public static IRepositorioPresente getInstance() {
        if (instance == null) {
            instance = new RepositorioPresente();
        }

        return instance;
    }

    @Override
    public List<Presente> getPresentes() {
        return this.presentes;
    }

    @Override
    public void cadastrarPresente(Presente presente) throws PresenteJaCadastradoException {
        if (existePresenteComDescricaoIgual(presente)) {
            throw new PresenteJaCadastradoException(presente);
        } else {
            presentes.add(presente);
        }
    }

    @Override
    public void adicionarPresenteNaListaDe(Pessoa pessoa, Presente presente) {
        if (!pessoaJaPossuiPresente(presente, pessoa)) {
            pessoa.getPresentesDesejados().add(presente);
        }
    }

    private boolean existePresenteComDescricaoIgual(Presente p) {
        for (Presente presente : presentes) {
            if (p.getDescricao().equals(presente.getDescricao())) {
                return true;
            }
        }

        return false;
    }

    private boolean pessoaJaPossuiPresente(Presente presente, Pessoa pessoa) {
        for (Presente presentePessoa : pessoa.getPresentesDesejados()) {
            if (presentePessoa.getDescricao().equals(presente.getDescricao())) {
                return true;
            }
        }

        return false;
    }

}
