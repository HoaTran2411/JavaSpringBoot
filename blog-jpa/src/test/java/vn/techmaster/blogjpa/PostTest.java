package vn.techmaster.blogjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.blogjpa.model.Post;
import vn.techmaster.blogjpa.repository.PostRepo;

@DataJpaTest
public class PostTest {

    @Autowired
    PostRepo postRepo;

    @Test
    public void getAllPostsTest() {
        postRepo.findAll().stream().map(Post::getTitle).forEach(System.out::println);
    }

    @Test
    public void findPostByIdTest() {
        var post = postRepo.findById(1L).get();
        System.out.println(post.getTitle());
    }

    @Test
    public void insertPostTest() {
        Post newPost = Post.builder().title("How to learn English").build();
        postRepo.save(newPost);
        postRepo.findAll().stream().map(Post::getTitle).distinct().forEach(System.out::println);
    }

    @Test
    public void updateUserTest() {
        Post post = postRepo.findById(1L).get();
        post.setTitle("Update news");
        postRepo.save(post);
        postRepo.findAll().stream().map(Post::getTitle).forEach(System.out::println);
    }

    @Test
    public void deleteUserTest() {
        postRepo.deleteById(1L);
        postRepo.findAll().stream().map(Post::getTitle).forEach(System.out::println);
    }

}
