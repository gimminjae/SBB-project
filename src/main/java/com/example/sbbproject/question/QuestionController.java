package com.example.sbbproject.question;

import com.example.sbbproject.UtilDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @RequestMapping("/question/list")
    public String questionList(Model model) {
        List<Question> questionList = questionService.getAll();
        List<QuestionDto> questionDtoList = UtilDto.toDtoList(questionList);
        model.addAttribute("questionList", questionDtoList);
        return "question_list";
    }
    @RequestMapping("/question/detail/{questionId}")
    public String questionDetail(Model model, @PathVariable(value = "questionId") Long questionId) {
        QuestionDto questionDto = UtilDto.toDto(questionService.getQuestionById(questionId));
        model.addAttribute("question", questionDto);
        return "question_detail";
    }
}
