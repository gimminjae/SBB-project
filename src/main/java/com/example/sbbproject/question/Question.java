package com.example.sbbproject.question;

import com.example.sbbproject.answer.Answer;
import com.example.sbbproject.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Question {
    @Id //기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //strategy는 고유번호를 생성하는 옵션으로 GenerationType.IDENTITY는 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용
    private Long id;

    @Column(length = 200) //컬럼의 길이 = 200
    private String subject;

    @Column(columnDefinition = "TEXT") //텍스트 형식 길이의 제한 x
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    private Set<SiteUser> voter;

    public QuestionDto toDto() {
        return new QuestionDto(this.id, this.subject, this.content, this.createDate, this.author);
    }

}
