package annuaireapp.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    public Optional<T> get(long id);

    public List<T> getAll();

    public void add(T t);

    public void update(T t, String[] params);

    public void remove(T t);
}
