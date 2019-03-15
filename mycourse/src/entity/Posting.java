package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "posting")
@Table
public class Posting {
    @Id
    private String id;
    private String topic;
    private String content;
    private String cid;
    private String poster_name;
    private String time;

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getCid() {
        return cid;
    }

    public String getTopic() {
        return topic;
    }

    public String getPoster_name() {
        return poster_name;
    }
}
