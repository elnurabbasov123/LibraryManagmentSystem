package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private String comment;
    private int bookId;

    public Comment(String comment, int bookId) {
        this.comment = comment;
        this.bookId = bookId;
    }
}