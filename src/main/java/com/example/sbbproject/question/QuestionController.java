package com.example.sbbproject.question;

import com.example.sbbproject.UtilDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
