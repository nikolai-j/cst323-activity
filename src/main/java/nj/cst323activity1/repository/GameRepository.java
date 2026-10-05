package nj.cst323activity1.repository;

import nj.cst323activity1.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
