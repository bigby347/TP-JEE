package annuaireapp.dao;

import annuaireapp.models.Groupe;

import java.util.List;
import java.util.Optional;

public class GroupeDao implements IDao<Groupe> {

    @Override
    public Optional<Groupe> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Groupe> getAll() {
        return null;
    }

    @Override
    public void add(Groupe groupe) {

    }

    @Override
    public void update(Groupe groupe, String[] params) {

    }

    @Override
    public void remove(Groupe groupe) {

    }
}
