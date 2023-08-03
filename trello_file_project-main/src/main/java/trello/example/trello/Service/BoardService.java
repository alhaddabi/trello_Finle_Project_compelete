package trello.example.trello.Service;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trello.example.trello.Models.Board;
import trello.example.trello.Repository.BoardRepository;
import trello.example.trello.RequestObject.GetBoardRequest;
import trello.example.trello.ResponseObject.GetBoardResponse;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public Board createBoard(Board board) {
        // Save the board using the BoardRepository
        return boardRepository.save(board);
    }


    //to get all the boards
    public List<Board> getBoard(){
        return boardRepository.findAll();
    }


    // get the boards by the ID
    public GetBoardResponse getBoardById(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            GetBoardResponse response = new GetBoardResponse();
            response.setBoardId(String.valueOf(board.getBoardId()));
            response.setTitle(board.getTitle());
            return response;
        }
        return null;
    }

// update board
    public GetBoardResponse updateBoard(Long boardId, GetBoardRequest updatedBoard) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(updatedBoard.getTitle());
            boardRepository.save(board);
            return getBoardById(boardId);
        }
        return null;
    }

    //delete board
    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    // find Board by ID
    public GetBoardResponse getBoardResponse(Long boardId)
    {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            GetBoardResponse response = new GetBoardResponse();
            response.setTitle(board.getTitle());
            return response;
        }
        return null;
    }

}


