package com.example.sbbproject.answer;

import com.example.sbbproject.UtilDto;
import com.example.sbbproject.question.Question;
import com.example.sbbproject.question.QuestionForm;
import com.example.sbbproject.question.QuestionService;
import com.example.sbbproject.user.SiteUser;
import com.example.sbbproject.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.Binding;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final UserService userService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{questionId}")
    @PreAuthorize("isAuthenticated()")
    public String createAnswer(Model model, @PathVariable("questionId") Long questionId,
                               @Valid AnswerForm answerForm, BindingResult bindingResult,
                               Principal principal) {
        SiteUser siteUser = userService.getUser(principal.getName());
        Question question = questionService.getQuestionById(questionId);
        if(bindingResult.hasErrors()) {
            model.addAttribute("question", UtilDto.toDto(question));
            model.addAttribute("answerList", UtilDto.toAnswerDtoList(question.getAnswerList()));
            return "question_detail";
        }
        answerService.create(answerForm.getContent(), question, siteUser);

        return "redirect:/question/detail/%d".formatted(questionId);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{answerId}")
    public String answerModify(AnswerForm answerForm, @PathVariable("answerId") Long answerId, Principal principal) {
        Answer answer = answerService.getAnswerById(answerId);
        if(!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "answer_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{answerId}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("answerId") Long answerId, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "answer_form";
        }
        Answer answer = answerService.getAnswerById(answerId);
        if(!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerService.modify(answer, answerForm.getContent());
        return "redirect:/question/detail/%d".formatted(answer.getQuestion().getId());
    }
}
