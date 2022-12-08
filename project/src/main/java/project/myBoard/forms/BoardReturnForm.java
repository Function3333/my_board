package project.myBoard.forms;

import lombok.Data;
import project.myBoard.categoryEnum.BoardCategory;

import java.util.List;

@Data
public class BoardReturnForm {
    private BoardCategory boardCategory;
    private List<PostReturnForm> postReturnForms;

    public BoardReturnForm(BoardCategory boardCategory, List<PostReturnForm> postReturnForms) {
        this.boardCategory = boardCategory;
        this.postReturnForms = postReturnForms;
    }
}
