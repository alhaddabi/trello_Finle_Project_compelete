package trello.example.trello.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trello.example.trello.Models.Board;

@Repository

public interface BoardRepository extends JpaRepository<Board, Long> {
}
