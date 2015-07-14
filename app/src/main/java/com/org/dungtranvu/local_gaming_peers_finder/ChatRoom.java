package com.org.dungtranvu.local_gaming_peers_finder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dungtranvu on 7/15/15.
 */
public class ChatRoom {
    private List<String> users;
    private List<ChatMessage> conversation;
    public ChatRoom(String user0, String user1) {
        users = new ArrayList<String>();
        users.add(user0);
        users.add(user1);
        conversation = new ArrayList<ChatMessage>();
    }

    public void addMessage(ChatMessage msg) {
        conversation.add(msg);
    }

    public List<ChatMessage> getConversation(int numberofmessages) {
        List<ChatMessage> result = new ArrayList<ChatMessage>();
        int min = (conversation.size() < numberofmessages) ? conversation.size(): numberofmessages;
        return result;
    }
}
