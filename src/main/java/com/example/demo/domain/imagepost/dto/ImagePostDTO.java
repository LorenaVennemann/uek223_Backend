package com.example.demo.domain.imagepost.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.UserMinimalDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a data transfer object for image posts, containing information about the image, its description,
 * like count, and the user who posted it. Extends {@link AbstractDTO} to include common DTO functionalities.
 */
@Getter
@Setter
public class ImagePostDTO extends AbstractDTO {

    private String image;
    private String description;
    private Integer like_count;
    private UserMinimalDTO user;

    /**
     * Constructs a new ImagePostDTO with specified details.
     *
     * @param id The unique identifier of the image post.
     * @param image The URL or base64 encoded string of the image.
     * @param description A brief description or caption for the image.
     * @param like_count The number of likes the image has received.
     */
    public ImagePostDTO(UUID id, String image, String description, Integer like_count){
        super(id); // Initializes the base class with a new UUID
        this.image = image;
        this.description = description;
        this.like_count = like_count;
    }
}
