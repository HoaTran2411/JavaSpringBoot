package vn.techmaster.blogjpa;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.blogjpa.model.Post;
import vn.techmaster.blogjpa.model.User;
import vn.techmaster.blogjpa.repository.PostRepo;
import vn.techmaster.blogjpa.repository.UserRepo;

@DataJpaTest
public class UserTest {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @Test
    public void getAllUsersTest() {
        userRepo.findAll().stream().map(User::getName).forEach(System.out::println);
    }

    // Test case liên quan đến fetch mode: EAGER, LAZY
    @Test
    public void findUserByIdTest() {
        var user = userRepo.findById(1L).get();
        // dùng EAGER ở "private List<Post> posts", sẽ in luôn câu lệnh sql select bảng
        // post
        // dùng Lazy ở "private List<Comment> comments" khi lấy ra user với id bằng 1,
        // câu lệnh sql về comment liên quan chưa được in ra
        System.out.println(user.getName());
        // Sau khi comment được gọi ra, thì câu lệnh sql liên quan comment mới dc in ra:
        System.out.println("---------------check fetch mode: lazy----------------");
        user.getComments().stream().forEach(comment -> System.out.println(comment.getReview()));
    }

    @Test
    public void insertUserTest() {
        User newUser = User.builder().name("Mary").build();
        userRepo.save(newUser);
        userRepo.findAll().stream().map(User::getName).forEach(System.out::println);
    }

    @Test
    public void updateUserTest() {
        User user = userRepo.findById(1L).get();
        user.setName("Jessica");
        userRepo.save(user);
        userRepo.findAll().stream().map(User::getName).forEach(System.out::println);
    }

    // test case liên quan Cascade
    @Test
    public void deleteUserTest() {
        // các post liên quan user vs ID bằng 1
        List<Post> posts = userRepo.findById(1L).get().getPosts();
        posts.stream().forEach(post -> System.out.println(post.getTitle()));
        Assertions.assertThat(posts.size()).isGreaterThan(0); // có các post liên quan user id bằng 1

        // xóa user có id bằng 1, post liên quan sẽ bị xóa theo
        userRepo.deleteById(1L);

        // in ra thấy các posts liên quan tới id 1 đã bị xóa
        postRepo.findAll().stream().map(Post::getTitle).forEach(System.out::println);
        userRepo.findAll().stream().map(User::getName).forEach(System.out::println);
    }

}
