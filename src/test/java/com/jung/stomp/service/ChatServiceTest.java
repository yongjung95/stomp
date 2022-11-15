package com.jung.stomp.service;

import com.jung.stomp.ChatRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @Test
    void 채팅방_개설(){
        chatService.createRoom("채팅방_개설");

        List<ChatRoom> roomList = chatService.findAllRoom();

        for(ChatRoom room : roomList){
            System.out.println(room.getRoomName());
        }
    }
}