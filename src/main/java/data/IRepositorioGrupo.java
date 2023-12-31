package data;

import control.models.Grupo;
import control.models.Pessoa;
import exceptions.GrupoNaoFoiSorteadoException;
import exceptions.NomeDeGrupoJaCadastradoException;

import java.util.List;

public interface IRepositorioGrupo {

    List<Grupo> getGrupos();

    void cadastrarGrupo(Grupo grupo) throws NomeDeGrupoJaCadastradoException;

    void adicionarPessoaAoGrupo(Pessoa pessoa, Grupo grupo);

    Grupo getGrupoDeNome(String nome);

    Pessoa obterAmigoSecretoDe(String apelido, Grupo grupo) throws GrupoNaoFoiSorteadoException;

}
