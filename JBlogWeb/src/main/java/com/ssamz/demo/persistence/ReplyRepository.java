package com.ssamz.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssamz.demo.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
