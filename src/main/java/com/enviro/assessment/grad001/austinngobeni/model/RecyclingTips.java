package com.enviro.assessment.grad001.austinngobeni.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="recycling-tips")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String recyclingTip;
}
