package data;

import control.models.Pessoa;
import control.models.Presente;
import exceptions.ApelidoJaCadastradoException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPessoa implements IRepositorioPessoa {

    private List<Pessoa> pessoas;
    private static IRepositorioPessoa instance;

    private RepositorioPessoa() {
        this.pessoas = new ArrayList<>(0);
    }

    public static IRepositorioPessoa getInstance() {
        if (instance == null) {
            instance = new RepositorioPessoa();
        }

        return instance;
    }

    @Override
    public List<Pessoa> getPessoas() {
        return this.pessoas;
    }

    /* Método recebe uma pessoa como parâmetro e avalia se já existe um apelido igual já cadastrado.
    *  Se houver apelido igual cadastrado, atira uma exceção. Senão, adiciona a pessoa no repositório.
    */
    @Override
    public void cadastrarPessoa(Pessoa pessoa) throws ApelidoJaCadastradoException {
        if (existePessoaCadastradaComApelidoIgualA(pessoa.getApelido())) {
            throw new ApelidoJaCadastradoException(pessoa.getApelido());
        } else {
            this.pessoas.add(pessoa);
        }
    }

    @Override
    public void adicionarPresenteAListaDe(Pessoa pessoa, Presente presente) {
            pessoa.getPresentesDesejados().add(presente);
    }

    @Override
    public void removerPresenteDaListaDe(Pessoa pessoa, Presente presente) {
        pessoa.getPresentesDesejados().remove(presente);
    }

    private boolean existePessoaCadastradaComApelidoIgualA(String apelido) {
        for (Pessoa pessoa : this.pessoas) {
            if (apelido.equals(pessoa.getApelido())) {
                return true;
            }
        }

        return false;
    }

}
