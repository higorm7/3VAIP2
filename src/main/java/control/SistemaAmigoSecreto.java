package control;

import control.controllers.ControladorGrupo;
import control.controllers.ControladorPessoa;
import control.models.Grupo;
import control.models.Pessoa;
import exceptions.ApelidoJaCadastradoException;
import exceptions.NomeDeGrupoJaCadastradoException;

import java.util.List;

public class SistemaAmigoSecreto {

    private ControladorGrupo controladorGrupo;
    private ControladorPessoa controladorPessoa;

    private static SistemaAmigoSecreto instance;

    private SistemaAmigoSecreto() {
        this.controladorGrupo = ControladorGrupo.getInstance();
        this.controladorPessoa = ControladorPessoa.getInstance();
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

    public void cadastrarPessoa(Pessoa pessoa) throws ApelidoJaCadastradoException {
        this.controladorPessoa.cadastrarPessoa(pessoa);
    }

    public void cadastrarGrupo(Grupo grupo) throws NomeDeGrupoJaCadastradoException {
        this.controladorGrupo.cadastrarGrupo(grupo);
    }

}
