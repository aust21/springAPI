package com.enviro.assessment.grad001.austinngobeni.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "guidelines")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Guidelines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String guideline;
}
