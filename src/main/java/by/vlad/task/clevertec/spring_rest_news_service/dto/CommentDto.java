package by.vlad.task.clevertec.spring_rest_news_service.dto;

import by.vlad.task.clevertec.spring_rest_news_service.entity.News;

import java.util.Date;

public class CommentDto {

    private Integer id;
    private Date date;
    private String text;
    private String username;

    public CommentDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
