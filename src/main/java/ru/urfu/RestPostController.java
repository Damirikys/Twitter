package ru.urfu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController()
public class RestPostController {
    private User user = new User("Damir Armanov");
    private PostStorage postStorage = user.getPostStorage();

    @RequestMapping("/posts")
    public String posts() {
        String feed = postStorage.getFeed();

        return "<html>" +
                "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
                "<body>" +
                "<h1>"+ user.getUsername() +" = "+ postStorage.count() +"</h1>" +
                "<form action='addpost' method='post'>"+
                "<input type='text' name='text'></input><button>Send</button>"+
                "</form>"+
                "<ul class='messages'>"
                + feed +
                "</ul>"+
                "</body>"+
                "</html>";
    }

    @RequestMapping(value = "/addpost", method = RequestMethod.POST)
    public String addpost(@RequestParam(required = true) String text){
        Post post = new Post(text);
        postStorage.add(post);
        return "<script>document.location.href = '/posts'</script>";
    }

    @RequestMapping(value = "/delpost", method = RequestMethod.GET)
    public String delpost(@RequestParam(required = true) int id){
        postStorage.remove(id);
        return "<script>document.location.href = '/posts'</script>";
    }
}
