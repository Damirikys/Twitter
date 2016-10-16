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
    private static HashMap<Integer, Post> postStorage = new HashMap<>();
    private static int count = 0;

    public static void add(Post post){
        if(post.getText().isEmpty()) return;
        post.setId(count());
        postStorage.put(post.getId(), post);
        count++;
    }

    public static void remove(int id){
        postStorage.remove(id);
        count--;
    }

    public static void remove(Post post){
        remove(post.getId());
    }

    public static Post get(int index){
        return postStorage.get(index);
    }

    public static String getFeed(){
        List<Post> posts = getPosts();
        StringBuilder template = new StringBuilder();

        for(int i = count()-1; i >= 0; i--)
            template.append(getPostTemplate(posts.get(i)));

        return template.toString();
    }

    private static String getPostTemplate(Post post){
        return "<li>"+ post.getText()  +"   <span onclick=\"document.location.href='/delpost?id="+post.getId()+"'\">X</span></li>";
    }

    public static List<Post> getPosts(){
        List<Post> posts = new LinkedList(postStorage.values());
        return posts;
    }

    public static Stream stream(){
        return getPosts().stream();
    }

    public static int count(){
        return count;
    }

    public static boolean contains(Post post){
        return postStorage.containsValue(post);
    }

    public static int indexOf(Post post){
        return postStorage.get(post).getId();
    }
}
