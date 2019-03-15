package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "course")
@Table
public class Course {
    @Id
    private String id;
    private String name;
    //name id also only
    private String tid;
    private String content;
    private boolean checked;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTid() {
        return tid;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getContent() {
        return content;
    }
}
