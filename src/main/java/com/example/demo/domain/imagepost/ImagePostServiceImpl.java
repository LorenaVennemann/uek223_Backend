
package com.example.demo.domain.imagepost;


import com.example.demo.core.generic.AbstractService;
import com.example.demo.core.generic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ImagePostServiceImpl extends AbstractServiceImpl<ImagePost> implements AbstractService<ImagePost> {


  public ImagePostServiceImpl(ImagePostRepository repository) {
    super(repository);
  }
}
