package com.example.sbbproject.question;

import com.example.sbbproject.UtilDto;
import com.example.sbbproject.answer.AnswerDto;
import com.example.sbbproject.answer.AnswerForm;
import com.example.sbbproject.user.SiteUser;
import com.example.sbbproject.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final UserService userService;
    private final QuestionService questionService;
    @RequestMapping("/list")
    public String questionList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> questionList = questionService.getList(page);
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    @RequestMapping("/detail/{questionId}")
    public String questionDetail(Model model, @PathVariable(value = "questionId") Long questionId, AnswerForm answerForm) {
        Question question = questionService.getQuestionById(questionId);
        QuestionDto questionDto = UtilDto.toDto(question);
        List<AnswerDto> answerDtoList = UtilDto.toAnswerDtoList(question.getAnswerList());

        model.addAttribute("question", questionDto);
        model.addAttribute("answerList", answerDtoList);
        return "question_detail";
    }
    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{questionId}")
    public String questionModify(QuestionForm questionForm, @PathVariable("questionId") Long questionId, Principal principal) {
        Question question = questionService.getQuestionById(questionId);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{questionId}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, @PathVariable("questionId") Long questionId, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = questionService.getQuestionById(questionId);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/detail/%d".formatted(questionId);
    }
}
