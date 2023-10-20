package ee.mihkel.lemmikloomad.repository;

import ee.mihkel.lemmikloomad.entity.Lemmikloom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LemmikloomRepository extends JpaRepository<Lemmikloom, Integer> {
    // saab tagastada ainult Lemmikloom või List<Lemmikloom>

    Lemmikloom findFirstByOrderByKaalDesc();

    List<Lemmikloom> findAllByKaalBetween(double min, double max);

    //localhost:8080/findAllByKaalIsGreaterThan?min=1
    List<Lemmikloom> findAllByKaalIsGreaterThan(double min);

    //localhost:8080/findAllByKaalIsGreaterThanAndNimetusContains?min=2.1&nimetus=Koer
    List<Lemmikloom> findAllByKaalIsGreaterThanAndNimetusContains(double kaal, String nimetus);

}
