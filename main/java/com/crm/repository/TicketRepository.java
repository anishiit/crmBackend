package com.crm.repository;

import com.crm.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(String status);
    List<Ticket> findByPriority(String priority);
    List<Ticket> findByAssignedTo(String assignedTo);
    List<Ticket> findByCustomerContainingIgnoreCase(String customer);
    List<Ticket> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}

