package project.myBoard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Image {
    @Column(name = "image_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private String uploadName;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Image createImage(String storeName, String uploadName, Post post) {
        this.storeName = storeName;
        this.uploadName = uploadName;
        this.post = post;
        post.getImages().add(this);

        return this;
    }


}
