package ee.mihkel.lemmikloomad.repository;

import ee.mihkel.lemmikloomad.entity.Omanik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmanikRepository extends JpaRepository<Omanik, String> {
}
