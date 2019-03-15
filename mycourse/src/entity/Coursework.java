package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "coursework")
@Table
public class Coursework {
    @Id
    private String id;
    private String ccid;
    private String topic;
    private String content;
    private String time;
    private String ddl;
    private String scorefile_name;

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setScorefile_name(String scorefile_name) {
        this.scorefile_name = scorefile_name;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getCcid() {
        return ccid;
    }

    public String getContent() {
        return content;
    }

    public String getDdl() {
        return ddl;
    }

    public String getTopic() {
        return topic;
    }

    public String getScorefile_name() {
        return scorefile_name;
    }
}
