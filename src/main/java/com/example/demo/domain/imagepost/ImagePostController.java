package com.example.demo.domain.imagepost;

import com.example.demo.domain.imagepost.dto.ImagePostDTO;
import com.example.demo.domain.imagepost.dto.ImagePostMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/imagepost")
public class ImagePostController {

  private final ImagePostServiceImpl imagePostService;
  private final ImagePostMapper imagePostMapper;
  @Autowired
  private ImagePostController(ImagePostServiceImpl imagePostService, ImagePostMapper imagePostMapper){
    this.imagePostService = imagePostService;
    this.imagePostMapper = imagePostMapper;
  }
  @GetMapping("/{id}")
  public ResponseEntity<ImagePostDTO> retrieveById(@PathVariable UUID id) {
    ImagePost imagePost = imagePostService.findById(id);
    return new ResponseEntity<>(imagePostMapper.toDTO(imagePost), HttpStatus.OK);
  }

  @GetMapping({"", "/"})
  public ResponseEntity<List<ImagePostDTO>> retrieveAll(){
    List<ImagePost> imagePost = imagePostService.findAll();
    return new ResponseEntity<>(imagePostMapper.toDTOs(imagePost), HttpStatus.OK);
  }
  @PreAuthorize("hasAuthority('POST_CREATE')")
  @PostMapping({"", "/"})
  public ResponseEntity createImagePost(@Valid @RequestBody ImagePost imagePost){
    return ResponseEntity.ok().body(imagePostMapper.toDTO(imagePostService.save(imagePost)));
  }

  @PreAuthorize("hasAuthority('POST_DELETE')")
  @DeleteMapping("/{id}")
  public ResponseEntity deleteImagePost(@PathVariable ("id") UUID id){
    imagePostService.deleteById(id);
    return ResponseEntity.ok().body("Deleted publisher");
  }
  @PreAuthorize("hasAuthority('POST_UPDATE')")
  @PutMapping("/{id}")
  public ResponseEntity updateImagePost(@Valid @RequestBody ImagePostDTO imagePostDTO, @PathVariable ("id") UUID id){
    return ResponseEntity.ok().body(imagePostMapper.toDTO(imagePostService.updateById(id, imagePostMapper.fromDTO(imagePostDTO))));
  }
}
