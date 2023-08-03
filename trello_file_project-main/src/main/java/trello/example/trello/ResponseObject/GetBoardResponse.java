package trello.example.trello.ResponseObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardResponse {
    private String boardId; // Change the data type to String
    private String name;
    private String[] columns;

    // ... Add getters and setters for other properties ...

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }


    public void setTitle(String title) {
        this.name = title;
    }

}
