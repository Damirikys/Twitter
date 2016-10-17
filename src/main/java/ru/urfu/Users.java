package ru.urfu;

import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.List;

public class Users {
    private static List<User> users = new LinkedList<>();

    public static void add(User user) {
        users.add(user);
    }

    public static void remove(int index) {
        users.remove(index);
    }

    public static void remove(User user) {
        users.remove(user);
    }

    public static boolean contains(User user) {
        return users.contains(user);
    }

    public static int size(){
        return users.size();
    }
}
