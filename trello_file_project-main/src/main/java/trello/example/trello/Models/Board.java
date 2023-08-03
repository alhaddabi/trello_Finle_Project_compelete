package trello.example.trello.Models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;


@Setter
@Getter
@Data
@Entity
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String name;

    public String title ;

    @ElementCollection
    @CollectionTable(name = "board_columns", joinColumns = @JoinColumn(name = "board_id"))
    @MapKeyColumn(name = "column_index")
    @Column(name = "column_name")
    private Map<Integer, String> columns;

}



