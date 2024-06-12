package com.crystalskin.domain;


import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
    @Id
    private Long id;
    private String category;
    private String questionText;

    @ElementCollection
    private List<String> options;

}


