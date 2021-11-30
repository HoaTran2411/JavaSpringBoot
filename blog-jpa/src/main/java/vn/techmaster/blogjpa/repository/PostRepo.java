package vn.techmaster.blogjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.blogjpa.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
}
