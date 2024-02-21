package com.example.demo.domain.imagepost;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/imagepost")
public class ImagePostController {

  private ImagePostServiceImpl imagePostService;
  
}
