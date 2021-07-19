package by.vlad.task.clevertec.spring_rest_news_service.exception_handling;

/**
 * It is the class which transfers description of error to client
 */
public class IncorrectData {

    private String info;

    public IncorrectData() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
