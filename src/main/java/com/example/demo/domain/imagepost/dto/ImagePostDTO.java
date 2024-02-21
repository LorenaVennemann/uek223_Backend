package com.example.demo.domain.imagepost.dto;

import com.example.demo.core.generic.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagePostDTO extends AbstractDTO {

    private String image;
    private String description;
    private Integer likesCount;
    private Long authorId;

}
