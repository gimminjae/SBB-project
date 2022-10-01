package com.example.sbbproject.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    @RequestMapping("/question/list")
    public String questionList() {
        return "question_list";
    }
}
