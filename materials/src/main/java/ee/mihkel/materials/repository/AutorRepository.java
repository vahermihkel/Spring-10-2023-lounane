package ee.mihkel.materials.repository;

import ee.mihkel.materials.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,String> {
}
