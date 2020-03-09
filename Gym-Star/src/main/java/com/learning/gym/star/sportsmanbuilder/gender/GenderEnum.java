package com.learning.gym.star.sportsmanbuilder.gender;

public enum GenderEnum {
    M("Male"),
    F("Female");

    private String displayGender;

    GenderEnum(String displayGender){
        this.displayGender = displayGender;
    }

    public String getGender () {
        return displayGender;
    }
}
