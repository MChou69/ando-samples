package mc.com.pedago.async;

import java.util.List;

public class RssResponse {
    List<RssModel> items;
    String title;
    String description;
    String link;


    public RssResponse(List<RssModel> items, String title, String description, String link) {
        this.items = items;
        this.title = title;
        this.description = description;
        this.link = link;
    }
    public List<RssModel> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

}
