package picture.repository;

import java.util.List;

public interface IGeneralRepository<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T model);

    void remove(Long id);
}
