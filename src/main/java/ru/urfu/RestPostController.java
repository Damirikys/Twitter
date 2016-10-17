package ru.urfu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController()
public class RestPostController {
    static {
        new User("Damir Armanov");
        new User("Ilya Kuligin");
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feed(@RequestParam(required = true) long userId) {
        User user = Users.getUser(userId);
        PostStorage postStorage = user.getPostStorage();
        String feed = postStorage.getFeed();

        return "<html>" +
                "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
                "<body>" +
                "<h1>"+ user.getUsername() +" = "+ postStorage.count() +"</h1>" +
                "<a href='/users'><- Back to users list</a>"+
                "<form action='addpost' method='post'>"+
                "<input type='text' name='userId' value='"+ userId +"' style='display:none'></input>"+
                "<input type='text' name='text'></input><button>Send</button>"+
                "</form>"+
                "<ul class='messages'>"
                + feed +
                "</ul>"+
                "</body>"+
                "</html>";
    }

    @RequestMapping(value = "/users")
    public String users(){
        List<User> userList = Users.getUserList();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < userList.size(); i++)
            sb.append("<li><a href='/feed?userId="+userList.get(i).getUserId()+"'>"+userList.get(i).getUsername()+"</a></li>");

        return
                "<html>" +
                "<body>" +
                "<h1>All users</h1>" +
                "<ul>"
                    + sb.toString() +
                "</ul>" +
                "</body>" +
                "<html>";
    }

    @RequestMapping(value = "/addpost", method = RequestMethod.POST)
    public String addpost(@RequestParam(required = true) long userId, String text){
        Users
                .getUser(userId)
                .getPostStorage()
                .add(text);

        return "<script>document.location.href = '/feed?userId="+ userId +"'</script>";
    }

    @RequestMapping(value = "/delpost", method = RequestMethod.GET)
    public String delpost(@RequestParam(required = true) long userId, long postId){
        Users
                .getUser(userId)
                .getPostStorage()
                .remove(postId);

        return "<script>document.location.href = '/feed?userId="+ userId +"'</script>";
    }
}
