package ru.urfu;

import java.util.*;

public class Users {
    private static Map<Long, User> users = new LinkedHashMap<>();
    private static long countId = 0;

    public static void add(User user) {
        users.put(countId, user);
        countId++;
    }

    public static long getCountId(){
        return countId;
    }

    public static List<User> getUserList(){
        return new LinkedList<>(users.values());
    }

    public static void remove(long userId) {
        users.remove(userId);
    }

    public static boolean contains(User user) {
        return users.containsValue(user);
    }

    public static int size(){
        return users.size();
    }

    public static User getUser(long userId){
        return users.get(userId);
    }
}
