package by.vlad.task.clevertec.spring_rest_news_service.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "news",
    fetch = FetchType.LAZY)
    private List<Comment> comments;

    public News(Integer id, LocalDate date, String title, String text, List<Comment> comments) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.text = text;
        this.comments = comments;
    }

    public News() {
    }

    public static NewsBuilder builder() {
        return new NewsBuilder();
    }

    public Comment addCommentToNews(Comment comment) {
        if (comments == null) {
            synchronized (News.class) {
                if (comments == null) {
                    comments = new ArrayList<>();
                }
            }
        }
        comments.add(comment);
        return comment;
    }

    public Integer getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof News)) return false;
        final News other = (News) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        final Object this$comments = this.getComments();
        final Object other$comments = other.getComments();
        if (this$comments == null ? other$comments != null : !this$comments.equals(other$comments)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof News;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        final Object $comments = this.getComments();
        result = result * PRIME + ($comments == null ? 43 : $comments.hashCode());
        return result;
    }

    public String toString() {
        return "News(id=" + this.getId() + ", date=" + this.getDate() + ", title=" + this.getTitle() + ", text=" + this.getText() + ", comments=" + this.getComments() + ")";
    }

    public static class NewsBuilder {
        private Integer id;
        private LocalDate date;
        private String title;
        private String text;
        private List<Comment> comments;

        NewsBuilder() {
        }

        public NewsBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public NewsBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public NewsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NewsBuilder text(String text) {
            this.text = text;
            return this;
        }

        public NewsBuilder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public News build() {
            return new News(id, date, title, text, comments);
        }

        public String toString() {
            return "News.NewsBuilder(id=" + this.id + ", date=" + this.date + ", title=" + this.title + ", text=" + this.text + ", comments=" + this.comments + ")";
        }
    }
}
