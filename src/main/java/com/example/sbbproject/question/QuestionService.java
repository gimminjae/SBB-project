package com.example.sbbproject.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long question_id) {
        Optional<Question> optionalQuestion = questionRepository.findById(question_id);
        if(optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else return null;
    }
}
