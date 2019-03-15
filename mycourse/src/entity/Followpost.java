package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "followpost")
@Table
public class Followpost {
    @Id
    private String id;
    private String pid;
    private String follower_name;
    private String content;
    private String time;

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFollower_name(String follower_name) {
        this.follower_name = follower_name;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getFollower_name() {
        return follower_name;
    }

    public String getPid() {
        return pid;
    }
}
