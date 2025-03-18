package com.crm.repository;

import com.crm.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByStatus(String status);
    List<Contact> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
    List<Contact> findByCompanyContainingIgnoreCase(String company);
}

