package control;

import control.controllers.ControladorGrupo;
import control.controllers.ControladorPessoa;
import control.models.Pessoa;
import exceptions.ApelidoJaCadastradoException;

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

    public void cadastrarPessoa(Pessoa pessoa) throws ApelidoJaCadastradoException {
        this.controladorPessoa.cadastrarPessoa(pessoa);
    }
}
