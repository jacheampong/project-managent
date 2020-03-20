package com.ja.dev.pma.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ja.dev.pma.entities.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
