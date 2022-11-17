package com.jung.stomp.service;

import com.jung.stomp.ChatMessage;
import com.jung.stomp.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoom> chatRooms;

    private Map<String, List<ChatMessage>> chatMessages;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
        chatMessages = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public ChatMessage sendMessage(ChatMessage message) {
        if (chatMessages.containsKey(message.getRoomId())) {
            List<ChatMessage> roomChatMessages = chatMessages.get(message.getRoomId());
            roomChatMessages.add(message);
            chatMessages.replace(message.getRoomId(), roomChatMessages);
        }else {
            List<ChatMessage> roomChatMessages = new ArrayList<>();
            roomChatMessages.add(message);
            chatMessages.put(message.getRoomId(), roomChatMessages);
        }

        return message;
    }

    public List<ChatMessage> MessageList(String roomId) {
        return chatMessages.get(roomId);
    }
}