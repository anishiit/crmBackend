package com.crm.controller;

import com.crm.dto.TicketDto;
import com.crm.model.Ticket;
import com.crm.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TicketDto>> getTicketsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(ticketService.getTicketsByStatus(status));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TicketDto>> getTicketsByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(ticketService.getTicketsByPriority(priority));
    }

    @GetMapping("/assignee/{assignedTo}")
    public ResponseEntity<List<TicketDto>> getTicketsByAssignee(@PathVariable String assignedTo) {
        return ResponseEntity.ok(ticketService.getTicketsByAssignee(assignedTo));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TicketDto>> searchTickets(@RequestParam String query) {
        return ResponseEntity.ok(ticketService.searchTickets(query));
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.createTicket(ticket), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @Valid @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}

