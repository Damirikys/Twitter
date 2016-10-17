package ru.urfu;

import java.awt.geom.Point2D;
import java.io.IOError;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletResponse;

public class PostStorage {
    private HashMap<Integer, Post> postStorage;
    private int count;
    private final long userId;

    PostStorage(long userId){
        this.userId = userId;
        postStorage = new HashMap<>();
        count = 0;
    }

    public void add(String message){
        Post post = new Post(message, userId+"_"+count);
        if(post.getText().isEmpty()) return;
        post.setIndex(count());
        postStorage.put(post.getIndex(), post);
        count++;
    }

    public void remove(int id){
        postStorage.remove(id);
        count--;
    }

    public Post get(int index){
        return postStorage.get(index);
    }

    public int count(){
        return count;
    }

    public boolean contains(Post post){
        return postStorage.containsValue(post);
    }

    public int indexOf(Post post){
        return postStorage.get(post).getIndex();
    }

    public long getUserId() {
        return userId;
    }

    public String getFeed(){
        List<Post> posts = getList();
        StringBuilder template = new StringBuilder();

        for(int i = count()-1; i >= 0; i--)
            template.append(getPostTemplate(posts.get(i)));

        return template.toString();
    }

    private String getPostTemplate(Post post){
        return "<li>"+ post.getText()  +"   <span onclick=\"document.location.href='/delpost?id="+post.getIndex()+"'\">X</span></li>";
    }

    public List<Post> getList(){
        List<Post> posts = new LinkedList(postStorage.values());
        return posts;
    }

    public Stream stream(){
        return getList().stream();
    }
}
