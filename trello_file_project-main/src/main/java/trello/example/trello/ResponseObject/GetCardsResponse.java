package trello.example.trello.ResponseObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCardsResponse {
    Long id;
    String title;
    String description;
    String section;

}
