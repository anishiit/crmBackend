package com.crm.service;

import com.crm.dto.ContactDto;
import com.crm.model.Contact;
import com.crm.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDto> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ContactDto getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + id));
        return convertToDto(contact);
    }

    public List<ContactDto> getContactsByStatus(String status) {
        return contactRepository.findByStatus(status).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ContactDto> searchContacts(String query) {
        return contactRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ContactDto createContact(Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return convertToDto(savedContact);
    }

    public ContactDto updateContact(Long id, Contact contactDetails) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + id));
        
        contact.setName(contactDetails.getName());
        contact.setEmail(contactDetails.getEmail());
        contact.setPhone(contactDetails.getPhone());
        contact.setCompany(contactDetails.getCompany());
        contact.setStatus(contactDetails.getStatus());
        
        Contact updatedContact = contactRepository.save(contact);
        return convertToDto(updatedContact);
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    private ContactDto convertToDto(Contact contact) {
        ContactDto dto = new ContactDto();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        dto.setCompany(contact.getCompany());
        dto.setStatus(contact.getStatus());
        dto.setCreatedAt(contact.getCreatedAt());
        return dto;
    }
}

