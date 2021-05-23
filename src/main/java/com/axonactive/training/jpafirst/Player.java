package com.axonactive.training.jpafirst;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private static Long autoId = 100000000L;

    private Long id;

    private String fullName;

    private Gender gender;

    private Company workAt;

    public Player(String fullName, Gender gender, Company workAt) {
        this.id = autoId++;
        this.fullName = fullName;
        this.gender = gender;
        this.workAt = workAt;
    }

}