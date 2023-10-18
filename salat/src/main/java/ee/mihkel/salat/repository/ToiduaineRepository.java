package ee.mihkel.salat.repository;

// JpaRepository ---> Flush (kustuta kõik)
// PagingAndSortingRepository ---> Võtta kõik ja korraga ka sorteerida
//                                  Võta kõik lehekülje kaupa
// CrudRepository ---> kõige basicum, Lisa, Eemalda, Muuda, Võta 1, Võta kõik

import ee.mihkel.salat.entity.Toiduaine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToiduaineRepository extends JpaRepository<Toiduaine, String> {
}
