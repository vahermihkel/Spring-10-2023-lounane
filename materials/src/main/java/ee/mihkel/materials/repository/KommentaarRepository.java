package ee.mihkel.materials.repository;

import ee.mihkel.materials.entity.Kommentaar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KommentaarRepository extends JpaRepository<Kommentaar,Long> {
}
