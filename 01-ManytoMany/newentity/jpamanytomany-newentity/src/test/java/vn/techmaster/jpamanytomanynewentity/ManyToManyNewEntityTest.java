package vn.techmaster.jpamanytomanynewentity;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.jpamanytomanynewentity.model.Course;
import vn.techmaster.jpamanytomanynewentity.model.CourseRegistration;
import vn.techmaster.jpamanytomanynewentity.model.Student;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ManyToManyNewEntityTest {
    @Autowired
    private EntityManager tem;

    @Test
    public void whenCourseRegistrationPersisted_thenCorrect() {
        Student student = new Student();
        tem.persist(student);

        Course course = new Course();
        tem.persist(course);

        CourseRegistration cRegistration = CourseRegistration.builder()
                .student(student).course(course).point(8.9).rating(100).build();

        tem.persist(cRegistration);

        CourseRegistration persistedCRegistration = tem.find(CourseRegistration.class, 1L);

        assertThat(persistedCRegistration).isNotNull();
        assertThat(persistedCRegistration.getStudent().getId()).isEqualTo(1L);
        assertThat(persistedCRegistration.getCourse().getId()).isEqualTo(1L);

    }
}
