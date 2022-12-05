package project.myBoard.forms;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;

@Data
@RequiredArgsConstructor
public class ImageReturnForm {
    private UrlResource urlResource;

    public ImageReturnForm createImageReturnForm(UrlResource urlResource) {
        this.urlResource = urlResource;
        return this;
    }

}
