package com.example.demo.domain.imagepost;

import com.example.demo.core.generic.AbstractService;
import com.example.demo.core.generic.AbstractServiceImpl;
import com.example.demo.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service implementation for managing image posts.
 * Provides functionality to add likes to posts and increment like counts.
 */
@Service
@Transactional
public class ImagePostServiceImpl extends AbstractServiceImpl<ImagePost> implements AbstractService<ImagePost> {

  private final ImagePostRepository imagePostRepository;
  private final PostLikesRepository postLikesRepository;

  /**
   * Constructs an ImagePostServiceImpl with the needed repositories.
   *
   * @param imagePostRepository Repository for image post operations.
   * @param userRepository      Repository for user operations.
   * @param postLikesRepository Repository for post likes operations.
   */
  public ImagePostServiceImpl(ImagePostRepository imagePostRepository, UserRepository userRepository, PostLikesRepository postLikesRepository) {
    super(imagePostRepository);
    this.imagePostRepository = imagePostRepository;
    this.postLikesRepository = postLikesRepository;
  }
  /*
  /**
   * Adds a like to a post
   *
   * @param userId ID of the user who likes the post.
   * @param postId ID of the post to be liked.
   */
  /*
  public void addLikeToPost(UUID userId, UUID postId) {
    PostLikes postLike = new PostLikes();
    postLike.setId(UUID.randomUUID());
    postLike.setUserId(userId);
    postLike.setPostId(postId);

    if (!postLikesRepository.existsByUserIdAndPostId(userId, postId)) {
      postLikesRepository.save(postLike);
    }
  }/*

  /**
   * Increments the like count of a specific post.
   *
   * @param postId ID of the post for which the like count is to be incremented.
   * @throws IllegalArgumentException if the post with the given ID is not found.
   */
  public void incrementLikeCount(UUID postId) {
    ImagePost imagePost = imagePostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));
    imagePost.setLike_count(imagePost.getLike_count() + 1);
    imagePostRepository.save(imagePost);
  }
}
