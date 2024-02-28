package com.example.demo.domain.imagepost.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.UserMinimalDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ImagePostDTO extends AbstractDTO {

    private String image;
    private String description;
    private Integer like_count;

    private UserMinimalDTO user;

    public ImagePostDTO(UUID id, String image, String description, Integer like_count){
        super(id);
        this.image = image;
        this.description = description;
        this.like_count = like_count;
    }



}
