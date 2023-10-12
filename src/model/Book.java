package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book  {
    private int id;
    private int libraryId;
    private String name;
    private String author;
    private String genre;
    private int copiesAvailable;
    private int likes;
    private int dislakes;
    private int buyCount;
    private double rentPrice;
    private List<String> comments;


    public Book(int id, int libraryId, String name, String author, String genre, int copiesAvailable, int likes, int dislakes, int buyCount, double rentPrice) {
        this.id = id;
        this.libraryId = libraryId;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.copiesAvailable = copiesAvailable;
        this.likes = likes;
        this.dislakes = dislakes;
        this.buyCount = buyCount;
        this.rentPrice = rentPrice;
    }

    public Book(int libraryId, String name, String author, String genre, int copiesAvailable, int likes, int dislakes, int buyCount, double rentPrice) {
        this.libraryId = libraryId;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.copiesAvailable = copiesAvailable;
        this.likes = likes;
        this.dislakes = dislakes;
        this.buyCount = buyCount;
        this.rentPrice = rentPrice;
    }
    public int getField(String field){
        int searchedInt=0;
        switch (field){
            case "copies_available" :
                searchedInt= getCopiesAvailable();
                break;
            case "buy_count":
                searchedInt=getBuyCount();
                break;
            case "likes":
                searchedInt=getLikes();
                break;
            case "dislakes":
                searchedInt=getDislakes();
                break;
            default:
                throw new RuntimeException("Searched Field Not Found");
        }
        return searchedInt;
    }

    @Override
    public String toString() {
        return "["+id+"].Book{" +
                "name :" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", copiesAvailable=" + copiesAvailable +
                ", likes=" + likes +
                ", dislakes=" + dislakes +
                ", buyCount=" + buyCount +
                ", rentPrice=" + rentPrice +"$"+
                '}'+"\n";
    }
}
