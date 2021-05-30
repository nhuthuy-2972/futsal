package com.axonactive.training.player;

import lombok.Getter;

public enum Gender {
    MALE(0), FEMALE(1), UNKNOWN(2);

    @Getter
    private int value;

    private Gender(int value) {
        this.value = value;
    }
}