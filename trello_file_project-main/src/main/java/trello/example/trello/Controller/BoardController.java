package trello.example.trello.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.example.trello.Models.Board;
import trello.example.trello.RequestObject.GetBoardRequest;
import trello.example.trello.ResponseObject.GetBoardResponse;
import trello.example.trello.Service.BoardService;

import java.util.*;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    BoardService boardService;

    private final Map<String, GetBoardResponse> boards = new HashMap<>();


// create the Board
    @PostMapping
    public ResponseEntity<GetBoardResponse> createBoard(@RequestBody GetBoardRequest request) {
        Board newBoard = new Board();
        newBoard.setTitle(request.getTitle());

        Board createdBoard = boardService.createBoard(newBoard);

        GetBoardResponse response = new GetBoardResponse();
        response.setBoardId(String.valueOf(createdBoard.getBoardId()));
        response.setName(createdBoard.getTitle());
        response.setColumns(getDefaultColumns());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private String[] getDefaultColumns() {
        return new String[] { "To do", "In progress", "Done" };
    }

    @GetMapping
    public List<Board> getProduct () {
        return boardService.getBoard();
    }

    //update board
    @PutMapping("/{boardId}")
    public ResponseEntity<GetBoardResponse> updateBoard(@PathVariable Long boardId, @RequestBody GetBoardRequest updatedBoard) {
        GetBoardResponse response = boardService.updateBoard(boardId, updatedBoard);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete board
    @DeleteMapping("/{boardId}")
    public void deleteBoard (@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
    }



    // get the Boards by ID
    @GetMapping("/{boardId}")
    public GetBoardResponse getBoardId (@PathVariable Long boardId) {
        return boardService.getBoardById(boardId);
    }

}