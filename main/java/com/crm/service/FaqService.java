package com.crm.service;

import com.crm.dto.FaqDto;
import com.crm.model.Faq;
import com.crm.repository.FaqRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaqService {

    private final FaqRepository faqRepository;

    @Autowired
    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<FaqDto> getAllFaqs() {
        return faqRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public FaqDto getFaqById(Long id) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ not found with id: " + id));
        return convertToDto(faq);
    }

    public List<FaqDto> getFaqsByCategory(String category) {
        return faqRepository.findByCategory(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FaqDto> searchFaqs(String query) {
        return faqRepository.findByQuestionContainingIgnoreCaseOrAnswerContainingIgnoreCase(query, query).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FaqDto> getTopFaqs() {
        return faqRepository.findTop5ByOrderByViewsDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public FaqDto createFaq(Faq faq) {
        Faq savedFaq = faqRepository.save(faq);
        return convertToDto(savedFaq);
    }

    public FaqDto updateFaq(Long id, Faq faqDetails) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ not found with id: " + id));
        
        faq.setQuestion(faqDetails.getQuestion());
        faq.setAnswer(faqDetails.getAnswer());
        faq.setCategory(faqDetails.getCategory());
        
        Faq updatedFaq = faqRepository.save(faq);
        return convertToDto(updatedFaq);
    }

    public FaqDto incrementViews(Long id) {
        Faq faq = faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ not found with id: " + id));
        
        faq.setViews(faq.getViews() + 1);
        Faq updatedFaq = faqRepository.save(faq);
        return convertToDto(updatedFaq);
    }

    public void deleteFaq(Long id) {
        if (!faqRepository.existsById(id)) {
            throw new EntityNotFoundException("FAQ not found with id: " + id);
        }
        faqRepository.deleteById(id);
    }

    private FaqDto convertToDto(Faq faq) {
        FaqDto dto = new FaqDto();
        dto.setId(faq.getId());
        dto.setQuestion(faq.getQuestion());
        dto.setAnswer(faq.getAnswer());
        dto.setCategory(faq.getCategory());
        dto.setViews(faq.getViews());
        dto.setCreatedAt(faq.getCreatedAt());
        return dto;
    }
}

