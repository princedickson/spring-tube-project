package com.Explict.springtube.project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "user")
public class User {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String fullName;
    private String emailAddress;
    private Set<String> subscriber;
    private Set<String> subscribeToUser;
    private List<String> videoHistory;
    private Set<String> likeVideos;
    private Set<String> dislikeVideos;
}
