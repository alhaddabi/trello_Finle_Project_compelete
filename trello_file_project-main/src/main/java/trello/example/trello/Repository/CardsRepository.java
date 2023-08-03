package trello.example.trello.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trello.example.trello.Models.Board;
import trello.example.trello.Models.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
}
