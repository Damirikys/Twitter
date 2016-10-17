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
    private Map<Long, Post> postStorage;
    private User user;
    private int countId;

    PostStorage(User user){
        postStorage = new LinkedHashMap<>();
        this.user = user;
        countId = 0;
    }

    public void add(String text) throws IllegalArgumentException {
        Post post = new Post(user, text, countId);
        postStorage.put((long)countId, post);
        countId++;
    }

    public User getUser() {
        return user;
    }

    public void remove(long postId){
        postStorage.remove(postId);
    }

    public Post get(long postId){
        return postStorage.get(postId);
    }

    public int count(){
        return postStorage.size();
    }

    public boolean contains(Post post){
        return postStorage.containsValue(post);
    }

    public String getFeed(){
        List<Post> posts = getList();
        StringBuilder template = new StringBuilder();

        for(int i = count()-1; i >= 0; i--)
            template.append(getPostTemplate(posts.get(i)));

        return template.toString();
    }

    private String getPostTemplate(Post post){
        return "<li>"+ post.getText()  +"   <span onclick=\"document.location.href='/delpost?id="+post.getId()+"'\">X</span></li>";
    }

    public List<Post> getList(){
        List<Post> posts = new LinkedList(postStorage.values());
        return posts;
    }

    public Stream stream(){
        return getList().stream();
    }
}
