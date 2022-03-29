package cs.hse.telesfor.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByPhoneNumber(String phoneNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Account a " +
            "SET a.enabled = TRUE WHERE a.phoneNumber = ?1")
    int enableUser(String phoneNumber);

    @Query("SELECT a FROM Account a WHERE a.role = ?1")
    List<Account> getAllUsersByRole(UserRole role);

}


