package com.learning.gym.star;

import java.util.Scanner;

public class UserInput {

     String getUserInput ( String expectedInput ) {
        System.out.println("Enter " + expectedInput.toLowerCase());
        Scanner enterTrainerName = new Scanner(System.in);
        return enterTrainerName.nextLine();
    }
}

