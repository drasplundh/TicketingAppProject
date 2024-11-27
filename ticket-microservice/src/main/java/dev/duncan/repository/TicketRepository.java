package dev.duncan.repository;

import dev.duncan.domain.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Modifying
    @Transactional
    @Query(value="UPDATE ticket SET approved=true, approvedBy=:username WHERE ticketId=:id", nativeQuery=true)
    void approveTicketById(
            @Param("id") int id,
            @Param("username") String username);

//    @Modifying
//    @Transactional
//    @Query(value="UPDATE ticket SET
//    void updateTicket(
//            @Param("id") int id);

}
