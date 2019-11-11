package com.learning.gym.star;

public enum GenderChoose {
    M("Male"),
    W("Female");

    private String displayGender;

    GenderChoose ( String displayGender ) {
        this.displayGender = displayGender;
    }

    public String getGender () {
        return displayGender;
    }
}
