package pl.kepski.busdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kepski.busdemo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.companyName = ?1, u.firstName = ?2, u.lastName = ?3 where u.id = ?4")
    void updateUserInfoById(String companyName, String firstName, String lastName, Long id);
}
