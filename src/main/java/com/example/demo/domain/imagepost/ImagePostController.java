package com.example.demo.domain.imagepost;

import com.example.demo.domain.imagepost.dto.ImagePostDTO;
import com.example.demo.domain.imagepost.dto.ImagePostMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/post")
public class ImagePostController {

  @Autowired
  private ImagePostServiceImpl imagePostService;
  @Autowired
  private ImagePostMapper imagePostMapper;

  /**
   * fetches all image posts
   */
  @GetMapping("/{id}")
  public ResponseEntity<ImagePostDTO> retrieveById(@PathVariable UUID id) {
    ImagePost imagePost = imagePostService.findById(id);
    return ResponseEntity.ok(imagePostMapper.toDTO(imagePost));
  }

  @GetMapping
  public ResponseEntity<List<ImagePostDTO>> retrieveAll() {
    List<ImagePost> imagePosts = imagePostService.findAll();
    return ResponseEntity.ok(imagePostMapper.toDTOs(imagePosts));
  }
  @PreAuthorize("hasAuthority('POST_CREATE')")
  @PostMapping("/add")
  public ResponseEntity<ImagePostDTO> createImagePost(@Valid @RequestBody ImagePostDTO imagePostDTO) {
    ImagePost savedImagePost = imagePostService.save(imagePostMapper.fromDTO(imagePostDTO));
    return new ResponseEntity<>(imagePostMapper.toDTO(savedImagePost), HttpStatus.CREATED);
  }

  @PreAuthorize("hasAuthority('POST_DELETE') or @imagePostSecurity.isOwner(authentication, #id)")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteImagePost(@PathVariable UUID id) {
    imagePostService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasAuthority('POST_UPDATE') or @imagePostSecurity.isOwner(authentication, #id)")
  @PutMapping("/{id}")
  public ResponseEntity<ImagePostDTO> updateImagePost(@Valid @RequestBody ImagePostDTO imagePostDTO, @PathVariable UUID id) {
    ImagePost updatedImagePost = imagePostService.updateById(id, imagePostMapper.fromDTO(imagePostDTO));
    return ResponseEntity.ok(imagePostMapper.toDTO(updatedImagePost));
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping("/{Id}")
  public ResponseEntity<?> toggleLike(@PathVariable UUID postId, @AuthenticationPrincipal UserDetails userDetails) {
    UUID userId = extractUserIdFromUserDetails(userDetails);
    imagePostService.addLikeToPost(userId, postId);
    imagePostService.incrementLikeCount(postId);
    return ResponseEntity.ok("Post liked successfully");
  }
  private UUID extractUserIdFromUserDetails(UserDetails userDetails) {
    return UUID.fromString(userDetails.getUsername());
  }


}
