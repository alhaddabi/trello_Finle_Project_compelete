package trello.example.trello.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.example.trello.Models.Board;
import trello.example.trello.Models.Cards;
import trello.example.trello.RequestObject.GetBoardRequest;
import trello.example.trello.RequestObject.GetCardsRequest;
import trello.example.trello.ResponseObject.GetBoardResponse;
import trello.example.trello.ResponseObject.GetCardsResponse;
import trello.example.trello.Service.CardsService;

import java.util.List;

@RestController
@RequestMapping("/api/cards")

public class CardsController {

    @Autowired
    CardsService cardsService;

    @PostMapping("/{board_id}")
    public ResponseEntity<GetCardsResponse> addCardToBoard(@PathVariable("board_id") Long boardId, @RequestBody GetCardsRequest cardRequest) {
        // Create a new Card object based on the request data
        Cards newCard = new Cards();
        newCard.setTitle(cardRequest.getTitle());
        newCard.setDescription(cardRequest.getDescription());
        newCard.setSection(cardRequest.getSection());
        Cards createdCard = cardsService.addCardToBoard(boardId, newCard);
        GetCardsResponse response = new GetCardsResponse();
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
    @PutMapping("/{cardsId}")
    public ResponseEntity<GetCardsResponse> updateCards(@PathVariable Long cardId, @RequestBody GetCardsRequest updateCards) {
        GetCardsResponse response = cardsService.updateCards(cardId, updateCards);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }


    // get the Cards by the ID
    @GetMapping("/{cardId}")
    public GetCardsResponse getCardId (@PathVariable Long cardId) {
        return cardsService.getCardsById(cardId);
    }


}
