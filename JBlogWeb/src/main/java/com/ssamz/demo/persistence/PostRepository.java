package com.ssamz.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssamz.demo.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
}
