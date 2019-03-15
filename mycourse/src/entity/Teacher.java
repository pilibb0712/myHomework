package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "teacher")
@Table
public class Teacher {
    @Id
    private String id;
    private String name;
    //name is only
    private String email;
    private String pic_name;
    private String password;


    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic_name() {
        return pic_name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
