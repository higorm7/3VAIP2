package data;

import control.models.Presente;
import exceptions.PresenteJaCadastradoException;

import java.util.List;

public interface IRepositorioPresente {

    List<Presente> getPresentes();

    void cadastrarPresente(Presente presente) throws PresenteJaCadastradoException;

}
