package com.fundamentosplatzi.fundamentos.repository;

import com.fundamentosplatzi.fundamentos.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

}
