
package com.example.demo.domain.imagepost;


import com.example.demo.core.generic.AbstractService;
import com.example.demo.core.generic.AbstractServiceImpl;
import com.example.demo.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ImagePostServiceImpl extends AbstractServiceImpl<ImagePost> implements AbstractService<ImagePost> {

  private final ImagePostRepository imagePostRepository;
  private final UserRepository userRepository;
  private final PostLikesRepository postLikesRepository;

  public ImagePostServiceImpl(ImagePostRepository imagePostRepository, UserRepository userRepository, PostLikesRepository postLikesRepository) {
    super(imagePostRepository);
    this.imagePostRepository = imagePostRepository;
    this.userRepository = userRepository;
    this.postLikesRepository = postLikesRepository;
  }

  public void addLikeToPost(UUID userId, UUID postId) {
    PostLikes postLike = new PostLikes();
    postLike.setId(UUID.randomUUID());
    postLike.setUserId(userId);
    postLike.setPostId(postId);

    if (!postLikesRepository.existsByUserIdAndPostId(userId, postId)) {
      postLikesRepository.save(postLike);
    }
  }

  public void incrementLikeCount(UUID postId) {
    ImagePost imagePost = imagePostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));
    imagePost.setLike_count(imagePost.getLike_count() + 1);
    imagePostRepository.save(imagePost);
  }
}
