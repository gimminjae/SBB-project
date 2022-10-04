package com.example.sbbproject.answer;

import com.example.sbbproject.user.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private Long id;
    private String content;
    private LocalDateTime createDate;
    private SiteUser author;
    private Set<SiteUser> voter;
}
