package data;

import control.models.Grupo;
import control.models.Pessoa;
import exceptions.NomeDeGrupoJaCadastradoException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGrupo implements IRepositorioGrupo {

    private List<Grupo> grupos;
    private static IRepositorioGrupo instance;

    private RepositorioGrupo() {
        this.grupos = new ArrayList<>(0);
    }

    public static IRepositorioGrupo getInstance() {
        if (instance == null) {
            instance = new RepositorioGrupo();
        }

        return instance;
    }

    @Override
    public List<Grupo> getGrupos() {
        return grupos;
    }

    /* Método que cadastra um grupo recebendo-o como parâmetro. Só realiza o cadastro se
     * não existir outro grupo com o mesmo nome cadastrado.
     */
    @Override
    public void cadastrarGrupo(Grupo grupo) throws NomeDeGrupoJaCadastradoException {
        if (existeGrupoCadastradoComONome(grupo.getNome())) {
            throw new NomeDeGrupoJaCadastradoException(grupo.getNome());
        } else {
            this.grupos.add(grupo);
        }
    }

    @Override
    public void adicionarPessoaAoGrupo(Pessoa pessoa, Grupo grupo) {
        grupo.getParticipantes().add(pessoa);
    }

    @Override
    public Grupo getGrupoDeNome(String nome) {
        for (Grupo grupo : grupos) {
            if (nome.equals(grupo.getNome())) {
                return grupo;
            }
        }

        return null;
    }

    private boolean existeGrupoCadastradoComONome(String nome) {
        for (Grupo grupo : this.grupos) {
            if (nome.equals(grupo.getNome())) {
                return true;
            }
        }

        return false;
    }

}
