package ru.urfu.entities;

import javax.persistence.*;

@Entity
@Table(name = "TBL_POST")
public class Post
{
    private static final int POST_MAX_LENGHT_TEXT = 150;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="userId", nullable = false)
    private int userId;
    @Column(name="text", nullable = false)
    private String text;

    public void setText(String text) throws IllegalArgumentException
    {
        if(text == null || text.isEmpty() || text.length() > POST_MAX_LENGHT_TEXT)
            throw new IllegalArgumentException("Invalid post text.");
        this.text = text.replace("<", "&lt;");
    }

    public String getText(){
        return text;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }
}
