package com.crm.service;

import com.crm.dto.TicketDto;
import com.crm.model.Ticket;
import com.crm.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TicketDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
        return convertToDto(ticket);
    }

    public List<TicketDto> getTicketsByStatus(String status) {
        return ticketRepository.findByStatus(status).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TicketDto> getTicketsByPriority(String priority) {
        return ticketRepository.findByPriority(priority).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TicketDto> getTicketsByAssignee(String assignedTo) {
        return ticketRepository.findByAssignedTo(assignedTo).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TicketDto> searchTickets(String query) {
        return ticketRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TicketDto createTicket(Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDto(savedTicket);
    }

    public TicketDto updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
        
        ticket.setTitle(ticketDetails.getTitle());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setStatus(ticketDetails.getStatus());
        ticket.setPriority(ticketDetails.getPriority());
        ticket.setAssignedTo(ticketDetails.getAssignedTo());
        ticket.setCustomer(ticketDetails.getCustomer());
        
        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToDto(updatedTicket);
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new EntityNotFoundException("Ticket not found with id: " + id);
        }
        ticketRepository.deleteById(id);
    }

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setPriority(ticket.getPriority());
        dto.setAssignedTo(ticket.getAssignedTo());
        dto.setCustomer(ticket.getCustomer());
        dto.setCreatedAt(ticket.getCreatedAt());
        return dto;
    }
}

