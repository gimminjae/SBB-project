package com.example.sbbproject.question;

import com.example.sbbproject.UtilDto;
import com.example.sbbproject.answer.AnswerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    @RequestMapping("/list")
    public String questionList(Model model) {
        List<Question> questionList = questionService.getAll();
        List<QuestionDto> questionDtoList = UtilDto.toQuestionDtoList(questionList);
        model.addAttribute("questionList", questionDtoList);
        return "question_list";
    }
    @RequestMapping("/detail/{questionId}")
    public String questionDetail(Model model, @PathVariable(value = "questionId") Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        QuestionDto questionDto = UtilDto.toDto(question);
        List<AnswerDto> answerDtoList = UtilDto.toAnswerDtoList(question.getAnswerList());

        model.addAttribute("question", questionDto);
        model.addAttribute("answerList", answerDtoList);
        return "question_detail";
    }
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
