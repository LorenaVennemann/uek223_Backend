package com.example.demo.domain.imagepost;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

/**
 * Represents an image post entity within the application,
 * extends {@link AbstractEntity} to inherit common entity properties.
 * Lifecycle callback methods are used to log actions when a new image post is created, deleted or updated.
 */

@Log4j2
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image_post")
public class ImagePost extends AbstractEntity {

  @Column
  private String image;

  @Column(length = 500)
  private String description;

  @Column
  private Integer like_count;


  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;

  @PostPersist
  public void newImagePostAdded(){
    log.info("Created a new post.");
  }

  @PostRemove
  public void imagePostDeleted(){
    log.info("deleted post.");
  }

  @PostUpdate
  public void imagePostUpdated(){
    log.info("Updated post.");
  }


  /**
   * Constructs a new ImagePost entity with specified details.
   *
   * @param id unique identifier of the image post
   * @param image or reference to the image
   * @param description description of the image post
   * @param like_count number of likes the post has received
   * @param author author of the post, got by a {@link User} entity
   */
  public ImagePost(UUID id, String image, String description, Integer like_count, User author){
    super(id);
    this.image = image;
    this.description = description;
    this.like_count = like_count;
    this.author = author;
  }
}