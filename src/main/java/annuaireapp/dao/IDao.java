package annuaireapp.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    public Optional<T> get(long id);

    public List<T> findAll();

    public void add(T t);

    public void update(T t);

    public void remove(T t);
}
