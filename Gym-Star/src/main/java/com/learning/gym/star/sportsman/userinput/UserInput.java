package com.learning.gym.star.sportsman.userinput;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UserInput {
     public String getUserInput ( String expectedInput ) {
        System.out.println("Enter " + expectedInput.toLowerCase());
        Scanner enterTrainerName = new Scanner(System.in, StandardCharsets.UTF_8);
        return enterTrainerName.nextLine();
    }
}

