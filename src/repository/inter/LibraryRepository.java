package repository.inter;

import model.Library;

import java.util.List;

public interface LibraryRepository {
    List<Library> getAll();
}
