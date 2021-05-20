package com.axonactive.training.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private static Long autoId = 100000000L;

    private Long id;

    private String name;

    public Company(String name){
        this.id = autoId++;
        this.name = name;
    }
}