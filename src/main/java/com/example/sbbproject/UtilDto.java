package com.example.sbbproject;

import com.example.sbbproject.answer.Answer;
import com.example.sbbproject.answer.AnswerDto;
import com.example.sbbproject.question.Question;
import com.example.sbbproject.question.QuestionDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class UtilDto {
    public static List<QuestionDto> toQuestionDtoList(List<Question> questionList) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : questionList) {
            questionDtoList.add(new QuestionDto(question.getId(), question.getSubject(), question.getContent(), question.getCreateDate(), question.getAuthor()));
        }
        return questionDtoList;
    }
    public static QuestionDto toDto(Question question) {
        return question.toDto();
    }
    public static List<AnswerDto> toAnswerDtoList(List<Answer> answerList) {
        List<AnswerDto> answerDtoList = new ArrayList<>();
        for(Answer answer : answerList) {
            answerDtoList.add(new AnswerDto(answer.getId(), answer.getContent(), answer.getCreateDate(), answer.getAuthor()));
        }
        return answerDtoList;
    }
    public static AnswerDto toDto(Answer answer) {
        return answer.toDto();
    }

    public static List<QuestionDto> toQuestionDtoPage(Page<Question> questionList) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : questionList) {
            questionDtoList.add(new QuestionDto(question.getId(), question.getSubject(), question.getContent(), question.getCreateDate(), question.getAuthor()));
        }
        return questionDtoList;
    }
}
