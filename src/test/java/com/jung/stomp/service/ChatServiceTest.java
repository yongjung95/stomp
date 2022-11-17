package com.jung.stomp.service;

import com.jung.stomp.ChatMessage;
import com.jung.stomp.ChatRoom;
import org.assertj.core.api.Assertions;
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
            System.out.println(room);
        }
    }

    @Test
    void 채팅방_입장(){
        ChatRoom room = chatService.createRoom("채팅방_입장");

        List<ChatRoom> roomList = chatService.findAllRoom();

        String roomId = roomList.get(0).getRoomId();

        ChatRoom findRoom = chatService.findById(roomId);

        Assertions.assertThat(room.getRoomName()).isNotEqualTo(findRoom.getRoomName());
    }

    @Test
    void 채팅방_메시지_전송() {
        ChatRoom room = chatService.createRoom("채팅방_입장");

        List<ChatRoom> roomList = chatService.findAllRoom();

        String roomId = roomList.get(0).getRoomId();

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(roomId);
        chatMessage.setSender("용정이");
        chatMessage.setMessage("안녕하세요??");

        chatService.sendMessage(chatMessage);

        List<ChatMessage> chatMessages = chatService.MessageList(roomId);

        for (ChatMessage message : chatMessages) {
            System.out.println(message);

        }
    }
}