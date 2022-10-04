package com.example.sbbproject.answer;

import com.example.sbbproject.DataNotFoundException;
import com.example.sbbproject.question.Question;
import com.example.sbbproject.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(String content, Question question, SiteUser siteUser) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(siteUser);
        answerRepository.save(answer);

        return answer;
    }

    public Answer getAnswerById(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        if(optionalAnswer.isPresent()) {
            return optionalAnswer.get();
        } else {
            throw new DataNotFoundException("데이터를 찾을 수 없습니다.");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }
}
