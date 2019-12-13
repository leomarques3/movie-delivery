package com.chassot.repositories;

import com.chassot.entities.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

    Set<UserAuthority> findByAuthority(String authority);

}
