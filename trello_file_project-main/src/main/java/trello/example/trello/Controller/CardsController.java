package trello.example.trello.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.example.trello.Models.Cards;
import trello.example.trello.RequestObject.GetCardsRequest;
import trello.example.trello.ResponseObject.GetCardsResponse;
import trello.example.trello.Service.CardsService;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin("*")
public class CardsController {

    @Autowired
    CardsService cardsService;

    // create the cards
    @PostMapping("/{board_id}")
    public ResponseEntity<GetCardsResponse> addCardToBoard(@PathVariable("board_id") Long boardId, @RequestBody GetCardsRequest cardRequest) {
        // Create a new Card object based on the request data
        Cards newCard = new Cards();
        newCard.setTitle(cardRequest.getTitle());
        newCard.setDescription(cardRequest.getDescription());
        newCard.setSection(cardRequest.getSection());
        Cards createdCard = cardsService.addCardToBoard(boardId, newCard);
        GetCardsResponse response = new GetCardsResponse();
        response.setId(createdCard.getCardId());
        response.setTitle(createdCard.getTitle());
        response.setDescription(createdCard.getDescription());
        response.setSection(createdCard.getSection());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // get all the cardes
    @GetMapping
    public List<Cards> getcards () {
        return cardsService.getCards();
    }




    //Delete Cards
    @DeleteMapping("/{cardId}")
    public void deleteCardById (@PathVariable Long cardId) {
        cardsService.deleteCardById(cardId);
    }



    // update the cards
    @PutMapping("/{board_id}/cards/{card_id}")
    public ResponseEntity<GetCardsResponse> updateCardOnBoard(
            @PathVariable("board_id") Long boardId,
            @PathVariable("card_id") Long cardId,
            @RequestBody GetCardsRequest updatedCardRequest
    ) {
        // Update the card using the cardService
        Cards updatedCard = cardsService.updateCardOnBoard(boardId, cardId, updatedCardRequest);

        if (updatedCard != null) {
            // Prepare the response body
            GetCardsResponse response = new GetCardsResponse();
            response.setTitle(updatedCard.getTitle());
            response.setDescription(updatedCard.getDescription());
            response.setSection(updatedCard.getSection());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // get the Cards by the ID
    @GetMapping("/{cardId}")
    public GetCardsResponse getCardId (@PathVariable Long cardId) {
        return cardsService.getCardsById(cardId);
    }


}
