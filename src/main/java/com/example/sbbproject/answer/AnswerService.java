package com.example.sbbproject.answer;

import com.example.sbbproject.question.Question;
import com.example.sbbproject.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(String content, Question question, SiteUser siteUser) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(siteUser);
        answerRepository.save(answer);
    }
}
