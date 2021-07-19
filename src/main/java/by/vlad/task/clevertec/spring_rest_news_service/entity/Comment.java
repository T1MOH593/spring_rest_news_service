package by.vlad.task.clevertec.spring_rest_news_service.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "text")
    private String text;

    @Column(name = "username")
    private String username;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
    fetch = FetchType.LAZY)
    @JoinColumn(name = "id_news")
    private News news;

    public Comment(Integer id, LocalDate date, String text, String username, News news) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.username = username;
        this.news = news;
    }

    public Comment() {
    }

    public static CommentBuilder builder() {
        return new CommentBuilder();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Comment;
    }

    public Integer getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getText() {
        return this.text;
    }

    public String getUsername() {
        return this.username;
    }

    public News getNews() {
        return this.news;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Comment)) return false;
        final Comment other = (Comment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$news = this.getNews();
        final Object other$news = other.getNews();
        if (this$news == null ? other$news != null : !this$news.equals(other$news)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $news = this.getNews();
        result = result * PRIME + ($news == null ? 43 : $news.hashCode());
        return result;
    }

    public String toString() {
        return "Comment(id=" + this.getId() + ", date=" + this.getDate() + ", text=" + this.getText() + ", username=" + this.getUsername() + ", news=" + this.getNews() + ")";
    }

    public static class CommentBuilder {
        private Integer id;
        private LocalDate date;
        private String text;
        private String username;
        private News news;

        CommentBuilder() {
        }

        public CommentBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public CommentBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public CommentBuilder text(String text) {
            this.text = text;
            return this;
        }

        public CommentBuilder username(String username) {
            this.username = username;
            return this;
        }

        public CommentBuilder news(News news) {
            this.news = news;
            return this;
        }

        public Comment build() {
            return new Comment(id, date, text, username, news);
        }

        public String toString() {
            return "Comment.CommentBuilder(id=" + this.id + ", date=" + this.date + ", text=" + this.text + ", username=" + this.username + ", news=" + this.news + ")";
        }
    }
}
