package com.example.demo.domain.imagepost;

import com.example.demo.core.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;

import java.util.UUID;

@Log4j2
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image_post")
public class ImagePost extends AbstractEntity {

  @Column
  private String image;

  @Column(length = 50)
  private String description;

  @Column
  private Integer likesCount;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;

  @Id
  private UUID id;

  @PostPersist
  public void newImagePostAdded(){
    log.info("create a new Post. ");
  }

  @PostUpdate
  public void imagePostUpdate(){
    log.info("Update Post. ");
  }

  public ImagePost(UUID id, String description, Integer likesCount, User author, String image){
    super(id);
    this.description = description;
    this.image = image;
    this.likesCount = likesCount;
    this.author = author;

  }
}