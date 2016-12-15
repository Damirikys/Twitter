package ru.urfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.urfu.entities.User;
import ru.urfu.entities.Post;
import ru.urfu.model.interfaces.PostDao;
import ru.urfu.model.interfaces.UserDao;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class IndexController
{
    @Autowired
    private UserDao userStorage;

    @Autowired
    private PostDao postStorage;

    @RequestMapping({"/feed"})
    public void feed(Model model, @RequestParam() int userId)
    {
        Optional<User> optional = userStorage.find(userId);

        if (optional.isPresent())
        {
            User user = optional.get();
            List<Post> userPosts = postStorage.getUserPosts(user);

            boolean itsMyFeed = false;
            User me = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (me.getId() == userId)
                itsMyFeed = true;

            model.addAttribute("userId", userId);
            model.addAttribute("myFeed", itsMyFeed);
            model.addAttribute("feed", getFeed(userPosts));
            model.addAttribute("username", user.getUsername());
            model.addAttribute("size", userPosts.size());
        }
        else
            throw new UsernameNotFoundException("User not found");
    }

    @RequestMapping({"/post"})
    public void post(Model model, @RequestParam() int id)
    {
        Optional<Post> optional = postStorage.getPostById(id);

        if (optional.isPresent())
        {
            model.addAttribute("post", optional.get());
        }
        else
            throw new NoSuchElementException("Post not found");
    }

    @RequestMapping({"/users"})
    public void users(Model model)
    {
        List<User> userList = userStorage.findAll();
        StringBuilder sb = new StringBuilder();
        for (User anUserList : userList) {
            sb.append("<li><a href='/feed?userId=")
                    .append(anUserList.getId())
                    .append("'>")
                    .append(anUserList.getUsername())
                    .append("</a></li>");
        }

        model.addAttribute("content", sb.toString());
    }

    @PostMapping(path = "addpost")
    public String addpost(int userId, String text)
    {
        Post post = new Post();
        post.setText(text);
        post.setUserId(userId);

        postStorage.create(post);

        return "redirect:/feed?userId=" + userId;
    }

    @GetMapping(path = "remove")
    public String remove(int postId)
    {
        Optional<Post> optional = postStorage.getPostById(postId);

        if (optional.isPresent())
        {
            User currentUser = (User) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            if (optional.get().getUserId() != currentUser.getId())
                throw new IllegalAccessError("Access error");

            postStorage.delete(optional.get());
            return "redirect:/feed?userId=" + optional.get().getUserId();
        }
        else
            throw new NoSuchElementException("Post not found");
    }

    private static String getFeed(List<Post> postList)
    {
        StringBuilder template = new StringBuilder();
        Collections.reverse(postList);
        for(Post post: postList)
            template.append("<li><a href='/post?id=")
                    .append(post.getId())
                    .append("'>")
                    .append(post.getText())
                    .append("</a>   <span>")
                    .append("<a href=\"/remove?postId=")
                    .append(post.getId())
                    .append("\">X</a>")
                    .append("</span></li>");

        return template.toString();
    }
}
