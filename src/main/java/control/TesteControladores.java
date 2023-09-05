package control;

import control.controllers.ControladorGrupo;
import control.controllers.ControladorPessoa;
import control.models.Grupo;
import control.models.Pessoa;
import exceptions.ApelidoJaCadastradoException;
import exceptions.NomeDeGrupoJaCadastradoException;

import java.time.LocalDate;

public class TesteControladores {

    public static void main(String[] args) {
        Pessoa p = new Pessoa("Higor", "Higor", "higor");
        Pessoa p2 = new Pessoa("Ha", "Ha", "higor");
        Grupo g = new Grupo("Higor", LocalDate.now());
        try {
            ControladorPessoa.getInstance().cadastrarPessoa(p);
            ControladorPessoa.getInstance().cadastrarPessoa(p2);
        } catch (ApelidoJaCadastradoException e) {
            System.out.println(e.getMessage());
        }

        try {
            ControladorGrupo.getInstance().cadastrarGrupo(g);
        } catch (NomeDeGrupoJaCadastradoException e) {
            System.out.println(e.getMessage());
        }

        ControladorGrupo.getInstance().adicionarPessoaAoGrupo(p, g);
        ControladorGrupo.getInstance().adicionarPessoaAoGrupo(p, g);

        for (Pessoa pessoa : ControladorPessoa.getInstance().getPessoas()) {
            System.out.println(pessoa);
        }

        for (Grupo grupo : ControladorGrupo.getInstance().getGrupos()) {
            System.out.println(grupo.getParticipantes());
        }

    }
}
