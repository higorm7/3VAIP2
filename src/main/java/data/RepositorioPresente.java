package data;

import control.models.Presente;
import exceptions.PresenteJaCadastradoException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPresente implements IRepositorioPresente {

    private List<Presente> presentes;
    private static IRepositorioPresente instance;

    private RepositorioPresente() {
        this.presentes = new ArrayList<>(0);
    }

    public static IRepositorioPresente getInstance() {
        if (instance == null) {
            instance = new RepositorioPresente();
        }

        return instance;
    }

    @Override
    public List<Presente> getPresentes() {
        return this.presentes;
    }

    @Override
    public void cadastrarPresente(Presente presente) throws PresenteJaCadastradoException {
        if (existePresenteComDescricaoIgual(presente)) {
            throw new PresenteJaCadastradoException(presente);
        } else {
            presentes.add(presente);
        }
    }

    private boolean existePresenteComDescricaoIgual(Presente p) {
        for (Presente presente : presentes) {
            if (p.getDescricao().equals(presente.getDescricao())) {
                return true;
            }
        }

        return false;
    }

}
