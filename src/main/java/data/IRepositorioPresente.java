package data;

import control.models.Pessoa;
import control.models.Presente;
import exceptions.PresenteJaCadastradoException;

import java.util.List;

public interface IRepositorioPresente {

    List<Presente> getPresentes();

    void cadastrarPresente(Presente presente) throws PresenteJaCadastradoException;

    void adicionarPresenteNaListaDe(Pessoa pessoa, Presente presente);

}
