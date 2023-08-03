package trello.example.trello.RequestObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trello.example.trello.Models.Board;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardRequest {
    Board board;
    private String title;

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
