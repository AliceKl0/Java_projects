package ru.fa.me;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :userId AND u.role <> 'ROLE_ADMIN'")
    void deleteUserByIdIfNotAdmin(@Param("userId") Long userId);
}
