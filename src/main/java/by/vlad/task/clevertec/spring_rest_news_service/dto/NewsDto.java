package by.vlad.task.clevertec.spring_rest_news_service.dto;

import by.vlad.task.clevertec.spring_rest_news_service.entity.Comment;

import java.time.LocalDate;
import java.util.List;

public class NewsDto {

    private Integer id;
    private LocalDate date;
    private String title;
    private String text;

    public NewsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
