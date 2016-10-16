package ru.urfu;


public class Post {
    private String text;
    private int id;

    Post(String text){
        setText(text.replace("<", "&lt;"));
        setId(-1);
    }

    private void setPost(Post post){
        setText(post.getText());
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
