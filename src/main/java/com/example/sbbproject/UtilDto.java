package com.example.sbbproject;

import com.example.sbbproject.question.Question;
import com.example.sbbproject.question.QuestionDto;

import java.util.ArrayList;
import java.util.List;

public class UtilDto {
    public static List<QuestionDto> toDtoList(List<Question> questionList) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : questionList) {
            questionDtoList.add(new QuestionDto(question.getId(), question.getSubject(), question.getContent(), question.getCreateDate()));
        }
        return questionDtoList;
    }
    public static QuestionDto toDto(Question question) {
        return question.toDto();
    }
}
