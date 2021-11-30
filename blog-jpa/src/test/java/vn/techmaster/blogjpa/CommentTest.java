package vn.techmaster.blogjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.blogjpa.model.Comment;
import vn.techmaster.blogjpa.repository.CommentRepo;

@DataJpaTest
public class CommentTest {
    @Autowired
    CommentRepo commentRepo;

    @Test
    public void getAllPostsTest() {
        commentRepo.findAll().stream().map(Comment::getReview).forEach(System.out::println);
    }

    @Test
    public void findPostByIdTest() {
        var post = commentRepo.findById(1L).get();
        System.out.println(post.getReview());
    }

    @Test
    public void insertPostTest() {
        Comment newPost = Comment.builder().review("Perfect!").build();
        commentRepo.save(newPost);
        commentRepo.findAll().stream().map(Comment::getReview).distinct().forEach(System.out::println);
    }

    @Test
    public void updateUserTest() {
        Comment user = commentRepo.findById(1L).get();
        user.setReview("Thank for your contribution!");
        commentRepo.save(user);
        commentRepo.findAll().stream().map(Comment::getReview).forEach(System.out::println);
    }

    @Test
    public void deleteUserTest() {
        commentRepo.deleteById(1L);
        commentRepo.findAll().stream().map(Comment::getReview).forEach(System.out::println);
    }
}
