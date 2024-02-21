package com.example.demo.domain.imagepost;

import com.example.demo.core.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;

@Log4j2
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image-post")
public class ImagePost extends AbstractEntity {

  @Column
  private String image;

  @Column(length = 50)
  private String description;

  @Column
  private Integer likes;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;

  @Id
  private Long id;

  @PostPersist
  public void newImagePostAdded(){
    log.info("create a new Post. ");
  }

  @PostUpdate
  public void imagePostUpdate(){
    log.info("Update Post. ");
  }

  public ImagePost(Long id, String description, Integer likes, User author, String image){
    super(id);
    this.description = description;
    this.image = image;
    this.likes = likes;
    this.author = author;

  }
}