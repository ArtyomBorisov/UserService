package it.academy.repositories;

import it.academy.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    List<UserEntity> findAllByOrderByEmailAsc();
    Page<UserEntity> findAllByOrderByEmailAsc(Pageable pageable);

    List<UserEntity> findAllByOrderByLastNameAsc();
    Page<UserEntity> findAllByOrderByLastNameAsc(Pageable pageable);
}
