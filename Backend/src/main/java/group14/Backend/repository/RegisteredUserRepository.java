package group14.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group14.Backend.model.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

    Optional<RegisteredUser> findRegisteredUserByUsername(String username);

    Optional<RegisteredUser> findRegisteredUserById(Long userId);

    @Query(value = "select t.registereduser_id from ticket t where t.ticket_id = :ticketId", nativeQuery = true)
    Long findRegisteredUserIdByTicketId(Long ticketId);
}
