package com.crm.repository;

import com.crm.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByCategory(String category);
    List<Faq> findByQuestionContainingIgnoreCaseOrAnswerContainingIgnoreCase(String question, String answer);
    List<Faq> findTop5ByOrderByViewsDesc();
}

