package com.crm.controller;

import com.crm.dto.ContactDto;
import com.crm.model.Contact;
import com.crm.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContactDto>> getContactsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(contactService.getContactsByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactDto>> searchContacts(@RequestParam String query) {
        return ResponseEntity.ok(contactService.searchContacts(query));
    }

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@Valid @RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable Long id, @Valid @RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.updateContact(id, contact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}

