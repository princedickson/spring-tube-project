package com.Explict.springtube.project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(value = "Video")
public class Video {

    @Id
    private String id;
    private String description;
    private String title;
    private String userId;
    private Integer like;
    private Integer dislike;
    private Set<String> tags;
    private String VideoUrl;
    private VideoStatus videoStatus;
    private Integer videoCount;
    private  String thumbnail;
    private List<Comment> commentList;
}
