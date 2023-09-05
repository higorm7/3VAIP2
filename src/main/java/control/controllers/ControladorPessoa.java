package control.controllers;

import control.models.Pessoa;
import data.IRepositorioPessoa;
import data.RepositorioPessoa;
import exceptions.ApelidoJaCadastradoException;

import java.util.List;

public class ControladorPessoa {

    private IRepositorioPessoa repositorioPessoa;
    private static ControladorPessoa instance;

    private ControladorPessoa() {
        this.repositorioPessoa = RepositorioPessoa.getInstance();
    }

    public static ControladorPessoa getInstance() {
        if (instance == null) {
            instance = new ControladorPessoa();
        }

        return instance;
    }

    public List<Pessoa> getPessoas() {
        return this.repositorioPessoa.getPessoas();
    }

    public void cadastrarPessoa(Pessoa pessoa) throws ApelidoJaCadastradoException {
        if (pessoa != null) {
            this.repositorioPessoa.cadastrarPessoa(pessoa);
        }
    }

}
