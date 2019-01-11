package mc.com.pedago.async;

import java.util.Date;

public class RssModel {
    public String title;
    public String link;
    public String description;
    public Date date;
    public String image;


    public RssModel(String title, String link, String description, Date date, String image) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.date=date;
        this.image=image;
    }

    public String getTitle() {
        return title;
    }
    public String getLink() {
        return link;
    }
    public String getDescription() {
        return description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RssModel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                //", description='" + description + '\'' +
                ", date=" + date +
                ", image='" + image + '\'' +
                '}';
    }

    /* @Override
    public String toString() {
        return "RssModel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }*/
}
