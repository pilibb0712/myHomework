package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "studentwork")
@Table
public class Studentwork {
    @Id
    private String id;
    private String cwid;
    private String sid;
    private String filename;
    private String file_url;

    public void setId(String id) {
        this.id = id;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getId() {
        return id;
    }

    public String getCwid() {
        return cwid;
    }

    public String getFilename() {
        return filename;
    }

    public String getFile_url() {
        return file_url;
    }

    public String getSid() {
        return sid;
    }
}
