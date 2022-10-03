package com.example.sbbproject.answer;

import com.example.sbbproject.UtilDto;
import com.example.sbbproject.question.Question;
import com.example.sbbproject.question.QuestionForm;
import com.example.sbbproject.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{questionId}")
    public String createAnswer(Model model, @PathVariable("questionId") Long questionId,
                               @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = questionService.getQuestionById(questionId);
        if(bindingResult.hasErrors()) {
            model.addAttribute("question", UtilDto.toDto(question));
            model.addAttribute("answerList", UtilDto.toAnswerDtoList(question.getAnswerList()));
            return "question_detail";
        }
        answerService.create(answerForm.getContent(), question);

        return "redirect:/question/detail/%d".formatted(questionId);
    }
}
