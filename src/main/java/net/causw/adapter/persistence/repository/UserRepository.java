package net.causw.adapter.persistence.repository;

import net.causw.adapter.persistence.user.User;
import net.causw.domain.model.enums.Role;
import net.causw.domain.model.enums.UserState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();

    Optional<User> findByNameAndStudentId(String name, String studentId);

    Optional<User> findByEmailAndNameAndStudentId(String email, String name, String studentId);

    Optional<User> findByEmail(String email);

    Optional<User> findByRefreshToken(String refreshToken);

    List<User> findByName(String name);

    List<User> findByRole(Role role);

    Page<User> findByStateOrderByCreatedAtAsc(UserState state, Pageable pageable);
}
