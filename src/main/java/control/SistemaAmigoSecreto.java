package control;

import control.controllers.ControladorGrupo;
import control.controllers.ControladorPessoa;
import control.controllers.ControladorPresente;
import control.models.Grupo;
import control.models.Pessoa;
import control.models.Presente;
import exceptions.ApelidoJaCadastradoException;
import exceptions.NomeDeGrupoJaCadastradoException;
import exceptions.PresenteJaCadastradoException;

import java.util.List;

public class SistemaAmigoSecreto {

    private ControladorGrupo controladorGrupo;
    private ControladorPessoa controladorPessoa;
    private ControladorPresente controladorPresente;

    private static SistemaAmigoSecreto instance;

    private SistemaAmigoSecreto() {
        this.controladorGrupo = ControladorGrupo.getInstance();
        this.controladorPessoa = ControladorPessoa.getInstance();
        this.controladorPresente = ControladorPresente.getInstance();
    }

    public static SistemaAmigoSecreto getInstance() {
        if (instance == null) {
            instance = new SistemaAmigoSecreto();
        }

        return instance;
    }

    public List<Pessoa> obterPessoas() {
        return this.controladorPessoa.getPessoas();
    }

    public List<Grupo> obterGrupos() {
        return this.controladorGrupo.getGrupos();
    }

    public List<Presente> obterPresentes() {
        return this.controladorPresente.getPresentes();
    }

    public void cadastrarPessoa(Pessoa pessoa) throws ApelidoJaCadastradoException {
        this.controladorPessoa.cadastrarPessoa(pessoa);
    }

    public void cadastrarGrupo(Grupo grupo) throws NomeDeGrupoJaCadastradoException {
        this.controladorGrupo.cadastrarGrupo(grupo);
    }

    public void cadastrarPresente(Presente presente) throws PresenteJaCadastradoException {
        this.controladorPresente.cadastrarPresente(presente);
    }

    public Grupo obterGrupoDeNome(String nome) {
        return this.controladorGrupo.getGrupoDeNome(nome);
    }

}
