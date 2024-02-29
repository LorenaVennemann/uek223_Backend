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

/**
 * Controller for handling image post requests.
 */
@Validated
@RestController
@RequestMapping("/post")
public class ImagePostController {

  @Autowired
  private ImagePostServiceImpl imagePostService;

  @Autowired
  private ImagePostMapper imagePostMapper;

  /**
   * Gets an image post by ID.
   *
   * @param id The UUID of the image post.
   * @return ResponseEntity containing the image post DTO.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ImagePostDTO> retrieveById(@PathVariable UUID id) {
    ImagePost imagePost = imagePostService.findById(id);
    return ResponseEntity.ok(imagePostMapper.toDTO(imagePost));
  }

  /**
   * Retrieves all image posts.
   *
   * @return ResponseEntity containing a list of image post DTOs.
   */
  @GetMapping
  public ResponseEntity<List<ImagePostDTO>> retrieveAll() {
    List<ImagePost> imagePosts = imagePostService.findAll();
    return ResponseEntity.ok(imagePostMapper.toDTOs(imagePosts));
  }

  /**
   * Creates a new image post.
   *
   * @param imagePostDTO image post DTO to create
   * @return ResponseEntity containing the created image post DTO
   */
  @PreAuthorize("hasAuthority('POST_CREATE')")
  @PostMapping("/add")
  public ResponseEntity<ImagePostDTO> createImagePost(@Valid @RequestBody ImagePostDTO imagePostDTO) {
    ImagePost savedImagePost = imagePostService.save(imagePostMapper.fromDTO(imagePostDTO));
    return new ResponseEntity<>(imagePostMapper.toDTO(savedImagePost), HttpStatus.CREATED);
  }

  /**
   * Deletes an image post by its ID.
   *
   * @param id UUID of the image post to delete
   * @return ResponseEntity with no content
   */
  @PreAuthorize("hasAuthority('POST_DELETE') or @imagePostSecurity.isOwner(authentication, #id)")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteImagePost(@PathVariable UUID id) {
    imagePostService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Updates an existing image post.
   *
   * @param imagePostDTO updated image post DTO
   * @param id UUID of the image post to update
   * @return ResponseEntity containing the updated image post DTO
   */
  @PreAuthorize("hasAuthority('POST_UPDATE') or @imagePostSecurity.isOwner(authentication, #id)")
  @PutMapping("/{id}")
  public ResponseEntity<ImagePostDTO> updateImagePost(@Valid @RequestBody ImagePostDTO imagePostDTO, @PathVariable UUID id) {
    ImagePost updatedImagePost = imagePostService.updateById(id, imagePostMapper.fromDTO(imagePostDTO));
    return ResponseEntity.ok(imagePostMapper.toDTO(updatedImagePost));
  }

  // This function has been commented out, but can be modified for usage
  /*
  @PreAuthorize("isAuthenticated()")
  @PutMapping("/{Id}")
  public ResponseEntity<?> toggleLike(@PathVariable UUID postId, @AuthenticationPrincipal UserDetails userDetails) {
    UUID userId = extractUserIdFromUserDetails(userDetails);
    imagePostService.addLikeToPost(userId, postId);
    imagePostService.incrementLikeCount(postId);
    return ResponseEntity.ok("Post liked successfully");
  }

  /**
   * Extracts the user ID from the UserDetails object.
   *
   * @param userDetails UserDetails object containing the user's information
   * @return UUID of the user
   */
  /*
  private UUID extractUserIdFromUserDetails(UserDetails userDetails) {
    return UUID.fromString(userDetails.getUsername());
  }
  */
}
