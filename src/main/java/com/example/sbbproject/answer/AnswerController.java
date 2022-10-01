package com.example.sbbproject.answer;

import com.example.sbbproject.question.Question;
import com.example.sbbproject.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{questionId}")
    public String createAnswer(@PathVariable("questionId") Long questionId,
                               @RequestParam(value = "content") String content) {
        Question question = questionService.getQuestionById(questionId);
        answerService.create(content, question);

        return "redirect:/question/detail/%d".formatted(questionId);
    }
}
