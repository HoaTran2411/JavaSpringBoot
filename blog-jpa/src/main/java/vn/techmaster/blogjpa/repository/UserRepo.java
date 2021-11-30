package vn.techmaster.blogjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.blogjpa.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
