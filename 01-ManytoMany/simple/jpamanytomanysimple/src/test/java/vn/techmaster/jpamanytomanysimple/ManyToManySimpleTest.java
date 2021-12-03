package vn.techmaster.jpamanytomanysimple;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.techmaster.jpamanytomanysimple.model.Course;
import vn.techmaster.jpamanytomanysimple.model.Student;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ManyToManySimpleTest {
    @Autowired
    private EntityManager tem;

    @Test
    public void test(){
        Course course1 = Course.builder().nameCourse("English").build();
        Course course2 = Course.builder().nameCourse("Math").build();
        Course course3 = Course.builder().nameCourse("Science").build();
        tem.persist(course1);
        tem.persist(course2);
        tem.persist(course3);

        List<Course> courses = List.of(course1, course2, course3);

        Student student = Student.builder().name("David").courses(courses).build();
        tem.persist(student);
        student.getCourses().stream().forEach(course -> System.out.println(course.getId()));
        assertThat(student).isNotNull();
        assertThat(student.getCourses().stream().map(Course::getId).collect(Collectors.toList())).contains(1L);



    }
}
