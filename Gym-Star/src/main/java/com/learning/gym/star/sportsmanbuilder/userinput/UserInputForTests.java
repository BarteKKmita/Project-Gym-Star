package com.learning.gym.star.sportsmanbuilder.userinput;

public class UserInputForTests implements UserText {
    private String name;
    private String surname;

    public UserInputForTests ( String name, String surname ) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getUserInput ( String expectedInput ) {
        if(expectedInput.equals("name")) {
            return name;
        }
        return surname;
    }
}
