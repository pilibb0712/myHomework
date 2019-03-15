package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "student")
@Table
public class Student {
    @Id
    private String id;
    private String name;
    //name is only
    private String email;
    private String pic_name;
    private String grade;
    private Boolean canceled;
    private String password;
    private String student_number;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isCanceled() {
        return canceled;
    }

    public String getGrade() {
        return grade;
    }

    public String getPic_name() {
        return pic_name;
    }

    public String getPassword() {
        return password;
    }

    public String getStudent_number() {
        return student_number;
    }
}
