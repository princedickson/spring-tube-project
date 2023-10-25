package com.Explict.springtube.project.Repository;

import com.Explict.springtube.project.Model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
