package com.example.ToDoAppSpring.document;

import static org.springframework.data.mongodb.core.schema.JsonSchemaObject.date;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import lombok.Data;

@Data
@Document(collection = "ToDos")
public class ToDo {
    @Id
    private String id;
    private String title;
    private Date date;
    private Boolean status;
    private String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public ToDo() {
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



    public ToDo(String title, String userId) {
        this.title = title;
        this.date = new Date();
        this.status = false;
        this.userId = userId;
    }
}
