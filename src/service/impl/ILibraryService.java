package service.impl;

import enums.Exception;
import exception.GeneralException;
import model.Library;
import repository.impl.ILibraryRepository;
import repository.inter.LibraryRepository;
import service.inter.LibraryService;

import java.util.List;

public class ILibraryService implements LibraryService {
    LibraryRepository libraryRepository=new ILibraryRepository();
    @Override
    public List<Library> getAll() {
        return libraryRepository.getAll();
    }
}
