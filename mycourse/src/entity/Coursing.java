package entity;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "coursing")
@Table
public class Coursing {
    @Id
    private String id;
    private String cid;
    private String cname;
    private String term;
    private String class_num;
    private int limit_num;
    private int take_num;
    private String choose_time;
    private String start_time;
    private String end_time;
    private String state;
    private String scorefile_name;

    public void setId(String id) {
        this.id = id;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setChoose_time(String choose_time) {
        this.choose_time = choose_time;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setLimit_num(int limit_num) {
        this.limit_num = limit_num;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTake_num(int take_num) {
        this.take_num = take_num;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setScorefile_name(String scorefile_name) {
        this.scorefile_name = scorefile_name;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getChoose_time() {
        return choose_time;
    }

    public String getId() {
        return id;
    }

    public String getCid() {
        return cid;
    }

    public int getLimit_num() {
        return limit_num;
    }

    public int getTake_num() {
        return take_num;
    }

    public String getClass_num() {
        return class_num;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getTerm() {
        return term;
    }

    public String getState() {
        return state;
    }

    public String getScorefile_name() {
        return scorefile_name;
    }

    public String getCname() {
        return cname;
    }
}
