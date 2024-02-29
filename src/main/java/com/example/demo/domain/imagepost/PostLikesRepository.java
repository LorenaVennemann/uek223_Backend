package com.example.demo.domain.imagepost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, UUID> {
    boolean existsByUserIdAndPostId(UUID userId, UUID postId);
}
