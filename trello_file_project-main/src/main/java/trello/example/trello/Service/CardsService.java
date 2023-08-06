package trello.example.trello.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trello.example.trello.Models.Board;
import trello.example.trello.Models.Cards;
import trello.example.trello.Repository.CardsRepository;
import trello.example.trello.RequestObject.GetBoardRequest;
import trello.example.trello.RequestObject.GetCardsRequest;
import trello.example.trello.ResponseObject.GetBoardResponse;
import trello.example.trello.ResponseObject.GetCardsResponse;


import java.util.*;

@Service
public class CardsService {

    @Autowired
    CardsRepository cardsRepository;

    // Example data store to hold boards and cards.
    private static Map<Long, List<Cards>> boardToCardsMap = new HashMap<>();

    public Cards addCardToBoard(Long boardId, Cards newCard) {
//        newCard.setSection("2"); // Set the section (if required)

        // Save the card to the database using the repository
        Cards savedCard = cardsRepository.save(newCard);

        // Return the saved card
        return savedCard;
    }


    // Helper method to generate a unique card ID (you can implement your own logic)
    private static Long generateUniqueCardId() {
        return new Random().nextLong();
    }

    // get all the cards
    public List<Cards> getCards(){
        return cardsRepository.findAll();
    }


    //delete Cards
    public void deleteCardById(Long cardId) {
        cardsRepository.deleteById(cardId);
    }



    // update Cards
    public GetCardsResponse updateCards(Long cardId, GetCardsRequest updateCards) {
        Optional<Cards> optionalCards = cardsRepository.findById(cardId);
        if (optionalCards.isPresent()) {
            Cards cards = optionalCards.get();
            cards.setTitle(updateCards.getTitle());
            cards.setTitle(updateCards.getDescription());
            cards.setTitle(updateCards.getSection());
            cardsRepository.save(cards);
            return getCardsById(cardId);
        }
        return null;
    }



    // get the cards by ID
    public GetCardsResponse getCardsById(Long cardId)
    {
        Optional<Cards> optionalCards = cardsRepository.findById(cardId);
        if (optionalCards.isPresent()) {
            Cards cards = optionalCards.get();
            GetCardsResponse response = new GetCardsResponse();
            response.setTitle(cards.getTitle());
            response.setDescription(cards.getDescription());
            response.setSection(cards.getSection());
            return response;
        }
        return null;
    }
    public Cards updateCardOnBoard(Long boardId, Long cardId, GetCardsRequest updatedCardRequest) {
        Optional<Cards> optionalCard = cardsRepository.findById(cardId);

        if (optionalCard.isPresent()) {
            Cards existingCard = optionalCard.get();

            // Update the card properties with the updatedCardRequest values
            existingCard.setTitle(updatedCardRequest.getTitle());
            existingCard.setDescription(updatedCardRequest.getDescription());
            existingCard.setSection(updatedCardRequest.getSection());

            // Save the updated card to the database
            Cards updatedCard = cardsRepository.save(existingCard);

            return updatedCard;
        }

        return null; // Card not found
    }
}
