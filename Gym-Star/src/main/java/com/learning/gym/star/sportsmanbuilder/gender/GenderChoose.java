package com.learning.gym.star.sportsmanbuilder.gender;

public enum GenderChoose {
    M("Male"),
    F("Female");

    private String displayGender;

    GenderChoose ( String displayGender ) {
        this.displayGender = displayGender;
    }

    public String getGender () {
        return displayGender;
    }
}
