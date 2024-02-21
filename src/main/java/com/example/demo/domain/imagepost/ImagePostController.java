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

  private ImagePostServiceImpl imagePostService;

  private ImagePostRepository imagePostRepository;

  @Autowired
  public ImagePostController(ImagePostServiceImpl imagePostService, ImagePostRepository imagePostRepository){
    this.imagePostRepository = imagePostRepository;
    this.imagePostService = imagePostService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ImagePostDTO> retrieveById(@PathVariable UUID id) {
    ImagePost imagePost = imagePostService.findById(id);
    return  new ResponseEntity<>(ImagePostMapper.toDTO(imagePost), HttpStatus.OK);
  }

  @GetMapping({"", "/"})
  public ResponseEntity<List<ImagePostDTO>> retrieveAll(){
    List<ImagePost> imagePost = imagePostService.findAll();
    return new ResponseEntity<>(ImagePostMapper.toDTOs(imagePost), HttpStatus.OK);
  }
  @PreAuthorize("hasAuthority('Post_Create')")
  @PostMapping({"", "/"})
  public ResponseEntity createImagePost(@Valid @RequestBody ImagePost imagePost){
    return ResponseEntity.ok().body(imagePostMapper.toDTO(imagePostService.save(imagePost)));
  }
}
