package com.org.dungtranvu.local_gaming_peers_finder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dungtranvu on 6/6/15.
 */
public class Message {
    private String content;
    private String username;
    private int likes;
    private int replies_count;
    private String replies;

    public Message(String username, String content, int likes, String replies) {
        this.content = content;
        this.username = username;
        this.likes = likes;
        this.replies = replies;
        // Read replies into arraylist

        this.replies_count = 0;
        for (int i = 0; i < replies.length(); i++)
            if (replies.charAt(i) == '\n')
                this.replies_count++;
    }
    public String getContent() {
        return content;
    }
    public String getUsername() {
        return username;
    }
    public int getLike() { return likes; }
    public String getReplies() { return replies; }
    public int getReplies_count() { return replies_count; }
}
