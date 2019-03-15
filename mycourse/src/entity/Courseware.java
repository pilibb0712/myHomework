package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "courseware")
@Table
public class Courseware {
    @Id
    private String id;
    private String cid;
    private String filename;
    private String file_url;

    public void setId(String id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setFile_url(String data_url) {
        this.file_url = data_url;
    }

    public String getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getCid() {
        return cid;
    }

    public String getFile_url() {
        return file_url;
    }
}
