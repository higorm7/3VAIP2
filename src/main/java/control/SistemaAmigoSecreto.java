package control;

import control.controllers.ControladorGrupo;
import control.controllers.ControladorPessoa;
import control.controllers.ControladorPresente;
import control.models.AmigosSecretos;
import control.models.Grupo;
import control.models.Pessoa;
import control.models.Presente;
import exceptions.*;

import java.security.SecureRandom;
import java.util.ArrayList;
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

    public Pessoa obterPessoaDeApelido(String apelido) {
        return this.controladorPessoa.getPessoaDeApelido(apelido);
    }

    public void sortear(Grupo grupo) throws GrupoJaSorteadoException, GrupoNaoContemPessoasSuficientesException {
        if (grupo.getParticipantes().size() <= 2) {
            throw new GrupoNaoContemPessoasSuficientesException(grupo);
        }

        if (grupo.getAmigosSecretos().isEmpty()) {
            SecureRandom secureRandom = new SecureRandom();
            List<Pessoa> pessoasDoGrupo = new ArrayList<>(grupo.getParticipantes());
            List<AmigosSecretos> amigosSecretos = new ArrayList<>();
            Pessoa presenteadora = pessoasDoGrupo.get(secureRandom.nextInt(pessoasDoGrupo.size()));
            pessoasDoGrupo.remove(presenteadora);
            Pessoa presenteada = null;

            while (!pessoasDoGrupo.isEmpty()) {
                presenteada = pessoasDoGrupo.get(secureRandom.nextInt(pessoasDoGrupo.size()));
                pessoasDoGrupo.remove(presenteada);

                AmigosSecretos amigos = new AmigosSecretos(presenteadora, presenteada);
                amigosSecretos.add(amigos);
                presenteadora = presenteada;
                pessoasDoGrupo.remove(presenteadora);
            }

            amigosSecretos.add(new AmigosSecretos(presenteada, amigosSecretos.get(0).getPresenteador()));

            grupo.setAmigosSecretos(amigosSecretos);
        } else {
            throw new GrupoJaSorteadoException(grupo);
        }
    }

    public boolean validarAcessoDe(Pessoa pessoa, String senha) throws SenhaIncorretaException {
        if (senha.equals(pessoa.getSenha())) {
            return true;
        } else {
            throw new SenhaIncorretaException(pessoa.getApelido());
        }
    }

    public Pessoa obterAmigoSecretoDe(String apelido, Grupo grupo) throws GrupoNaoFoiSorteadoException {
        return this.controladorGrupo.obterAmigoSecretoDe(apelido, grupo);
    }

}
