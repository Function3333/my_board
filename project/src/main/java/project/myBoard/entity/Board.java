package project.myBoard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.myBoard.categoryEnum.BoardCategory;
;import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "boardName")
    @Enumerated(EnumType.STRING)
    private BoardCategory category;

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();
}
