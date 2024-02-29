package com.example.demo.domain.imagepost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, UUID> {

    /**
     * Checks if a like already exists for a given user and post.
     *
     * @param userId the ID of the user
     * @param postId the ID of the post
     * @return true if a like already exists, false otherwise
     */
    boolean existsByUserIdAndPostId(UUID userId, UUID postId);
}
