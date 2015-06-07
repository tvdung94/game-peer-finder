package com.org.dungtranvu.local_gaming_peers_finder;

/**
 * Created by dungtranvu on 6/6/15.
 */
public class Message {
    String content;
    String username;
    int like;

    public Message(String username, String content, int like) {
        this.content = content;
        this.username = username;
        this.like = like;
    }
    public String getContent() {
        return content;
    }
    public String getUsername() {
        return username;
    }
    public int getLike() {
        return like;
    }
}
