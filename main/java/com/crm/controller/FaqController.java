package com.crm.controller;

import com.crm.dto.FaqDto;
import com.crm.model.Faq;
import com.crm.service.FaqService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@CrossOrigin(origins = "*")
public class FaqController {

    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping
    public ResponseEntity<List<FaqDto>> getAllFaqs() {
        return ResponseEntity.ok(faqService.getAllFaqs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaqDto> getFaqById(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getFaqById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<FaqDto>> getFaqsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(faqService.getFaqsByCategory(category));
    }

    @GetMapping("/top")
    public ResponseEntity<List<FaqDto>> getTopFaqs() {
        return ResponseEntity.ok(faqService.getTopFaqs());
    }

    @GetMapping("/search")
    public ResponseEntity<List<FaqDto>> searchFaqs(@RequestParam String query) {
        return ResponseEntity.ok(faqService.searchFaqs(query));
    }

    @PostMapping
    public ResponseEntity<FaqDto> createFaq(@Valid @RequestBody Faq faq) {
        return new ResponseEntity<>(faqService.createFaq(faq), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaqDto> updateFaq(@PathVariable Long id, @Valid @RequestBody Faq faq) {
        return ResponseEntity.ok(faqService.updateFaq(id, faq));
    }

    @PutMapping("/{id}/view")
    public ResponseEntity<FaqDto> incrementViews(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.incrementViews(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable Long id) {
        faqService.deleteFaq(id);
        return ResponseEntity.noContent().build();
    }
}

