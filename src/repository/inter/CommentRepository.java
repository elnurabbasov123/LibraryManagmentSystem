package repository.inter;

import model.Comment;

import java.util.List;

public interface CommentRepository {
    void add(Comment comment);
    List<String> getByBookId(int id);
}
