package control.controllers;

import control.models.AmigosSecretos;
import control.models.Grupo;
import control.models.Pessoa;
import data.IRepositorioGrupo;
import data.RepositorioGrupo;
import exceptions.GrupoNaoFoiSorteadoException;
import exceptions.NomeDeGrupoJaCadastradoException;

import java.util.List;

public class ControladorGrupo {

    private IRepositorioGrupo repositorioGrupo;
    private static ControladorGrupo instance;

    private ControladorGrupo() {
        this.repositorioGrupo = RepositorioGrupo.getInstance();
    }

    public static ControladorGrupo getInstance() {
        if (instance == null) {
            instance = new ControladorGrupo();
        }

        return instance;
    }

    public List<Grupo> getGrupos() {
        return this.repositorioGrupo.getGrupos();
    }

    public void cadastrarGrupo(Grupo grupo) throws NomeDeGrupoJaCadastradoException {
        if (grupo != null) {
            this.repositorioGrupo.cadastrarGrupo(grupo);
        }
    }

    public void adicionarPessoaAoGrupo(Pessoa pessoa, Grupo grupo) {
        if (pessoa != null && grupo != null) {
            this.repositorioGrupo.adicionarPessoaAoGrupo(pessoa, grupo);
        }
    }

    public Grupo getGrupoDeNome(String nome) {
        return this.repositorioGrupo.getGrupoDeNome(nome);
    }

    public Pessoa obterAmigoSecretoDe(String apelido, Grupo grupo) throws GrupoNaoFoiSorteadoException {
        return this.repositorioGrupo.obterAmigoSecretoDe(apelido, grupo);
    }

    public List<AmigosSecretos> obterAmigosSecretosDoGrupo(Grupo grupo) {
        return grupo.getAmigosSecretos();
    }

}
