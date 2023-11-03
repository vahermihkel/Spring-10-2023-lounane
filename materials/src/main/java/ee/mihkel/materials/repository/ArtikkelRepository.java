package ee.mihkel.materials.repository;

import ee.mihkel.materials.entity.Artikkel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikkelRepository extends JpaRepository<Artikkel,Long> {
}
