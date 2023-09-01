package data;

import control.models.Pessoa;
import control.models.Presente;
import exceptions.ApelidoJaCadastradoException;

import java.util.List;

public interface IRepositorioPessoa {

    List<Pessoa> getPessoas();

    void cadastrarPessoa(Pessoa pessoa) throws ApelidoJaCadastradoException;

    void adicionarPresenteAListaDe(Pessoa pessoa, Presente presente);

    void removerPresenteDaListaDe(Pessoa pessoa, Presente presente);

}
