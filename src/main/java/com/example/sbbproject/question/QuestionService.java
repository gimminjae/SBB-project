package com.example.sbbproject.question;

import com.example.sbbproject.DataNotFoundException;
import com.example.sbbproject.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return questionRepository.findBySubjectLike(pageable, "%" + kw + "%");
    }

    public Question getQuestionById(Long question_id) {
        Optional<Question> optionalQuestion = questionRepository.findById(question_id);
        if(optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else throw new DataNotFoundException("data not found");
    }

    public void create(String subject, String content, SiteUser siteUser) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(siteUser);
        questionRepository.save(question);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void modify(Question question, String subject, String content) {
        question.setModifyDate(LocalDateTime.now());
        question.setSubject(subject);
        question.setContent(content);
        questionRepository.save(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        questionRepository.save(question);
    }
}
