package com.org.dungtranvu.local_gaming_peers_finder;

/**
 * Created by dungtranvu on 7/15/15.
 */
public class ChatMessage {
    private String author;
    private String content;
    public ChatMessage(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
