package control.controllers;

import control.models.Pessoa;
import control.models.Presente;
import data.IRepositorioPresente;
import data.RepositorioPresente;
import exceptions.PresenteJaCadastradoException;

import java.util.List;

public class ControladorPresente {

    private IRepositorioPresente repositorioPresente;
    private static ControladorPresente instance;

    private ControladorPresente() {
        this.repositorioPresente = RepositorioPresente.getInstance();
    }

    public static ControladorPresente getInstance() {
        if (instance == null) {
            instance = new ControladorPresente();
        }

        return instance;
    }

    public List<Presente> getPresentes() {
        return this.repositorioPresente.getPresentes();
    }

    public void cadastrarPresente(Presente presente) throws PresenteJaCadastradoException {
        this.repositorioPresente.cadastrarPresente(presente);
    }

    public void adicionarPresenteNaListaDe(Pessoa pessoa, Presente presente) {
        this.repositorioPresente.adicionarPresenteNaListaDe(pessoa, presente);
    }

}
