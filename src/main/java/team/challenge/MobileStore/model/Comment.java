package team.challenge.MobileStore.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private String message;
    private Comment answer;
    private UserModel author;
    private LocalDateTime createdDate;
}