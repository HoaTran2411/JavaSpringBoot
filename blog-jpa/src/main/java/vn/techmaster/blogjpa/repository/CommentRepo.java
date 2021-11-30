package vn.techmaster.blogjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.blogjpa.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

}
