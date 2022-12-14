package com.example.sbbproject;

import com.example.sbbproject.question.Question;
import com.example.sbbproject.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbProjectApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void test_data() {
        for(int i = 1; i <= 50; i++) {
            Question question = new Question();
            question.setCreateDate(LocalDateTime.now());
            question.setSubject("subject%d".formatted(i));
            question.setContent("content%d".formatted(i));
            questionRepository.save(question);
        }
    }


}
