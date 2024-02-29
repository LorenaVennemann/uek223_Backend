package com.example.demo.domain.imagepost;

import com.example.demo.core.generic.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagePostRepository extends AbstractRepository<ImagePost> {
    public Page<ImagePost> findAll(Pageable pageable);
}
