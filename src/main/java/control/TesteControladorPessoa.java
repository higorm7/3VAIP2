package control;

import control.models.Pessoa;
import exceptions.ApelidoJaCadastradoException;

public class TesteControladorPessoa {

    public static void main(String[] args) {
        Pessoa p = new Pessoa("Higor", "Higor", "higor");
        try {
            ControladorPessoa.getInstance().cadastrarPessoa(p);
        } catch (ApelidoJaCadastradoException e) {
            System.out.println("Erro");
        }

        for (Pessoa pessoa : ControladorPessoa.getInstance().getPessoas()) {
            System.out.println(pessoa);
        }

    }
}
