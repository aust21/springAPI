package com.enviro.assessment.grad001.austinngobeni.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WasteCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String disposal;
}
