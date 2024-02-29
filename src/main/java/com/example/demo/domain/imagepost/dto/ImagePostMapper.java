package com.example.demo.domain.imagepost.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.imagepost.ImagePost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * This interface is for converting ImagePost and ImagePostDTO objects.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImagePostMapper extends AbstractMapper<ImagePost, ImagePostDTO> {
}
