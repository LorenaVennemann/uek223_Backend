package com.example.demo.domain.imagepost;

import com.example.demo.core.generic.AbstractRepository;
import com.example.demo.core.generic.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagePostServiceImpl extends AbstractServiceImpl<ImagePost> {

  @Autowired
  public ImagePostServiceImpl(AbstractRepository<ImagePost> repository) {
    super(repository);
  }
}

