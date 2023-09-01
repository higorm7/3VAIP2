package data;

import control.models.Grupo;
import control.models.Pessoa;
import exceptions.NomeDeGrupoJaCadastradoException;

import java.util.List;

public interface IRepositorioGrupo {

    List<Grupo> getGrupos();

    void cadastrarGrupo(Grupo grupo) throws NomeDeGrupoJaCadastradoException;

    void adicionarPessoaAoGrupo(Pessoa pessoa, Grupo grupo);

}
