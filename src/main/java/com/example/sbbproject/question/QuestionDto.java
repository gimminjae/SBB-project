package com.example.sbbproject.question;

import com.example.sbbproject.user.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private SiteUser author;
    private Set<SiteUser> voter;

}
