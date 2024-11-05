package demo.practice;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private Integer year;
    private String publisher;
    private List<Chapter> chapters;

    // Getters and Setters нужны, чтобы Jackson мог читать и записывать данные.
    //Но их использование необязательно. Они нужны, если поля приватные (чтобы защитить их от нежелательных изменений
    // и контролировать доступ к ним)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}

class Chapter {
    // Это свойство соответствует полю JSON показывает,
    // что при десериализации JSON-ключ "chapterNumber" должен быть сопоставлен с полем number в Java-классе
    @JsonProperty("chapterNumber")
    private int chapterNumber;
    private String title;

    // Getters and Setters
    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

