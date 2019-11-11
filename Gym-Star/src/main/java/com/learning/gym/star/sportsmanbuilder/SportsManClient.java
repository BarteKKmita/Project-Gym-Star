package com.learning.gym.star.sportsmanbuilder;

public class SportsManClient {
    public static void main ( String[] args ) {

        SportsManBuilder sportsManBuilder = new MaleSportsMan("Bartek", "Kmita");
        SportsManDirector director = new SportsManDirector(sportsManBuilder);
        director.setConcreteSportsMan();
        ConcreteSportsMan sportsMan = director.getConcreteSportsMan();
        System.out.println(sportsMan.getName()+" "+ sportsMan.getSurname());
        System.out.println(sportsMan.getGender());
        System.out.println(sportsMan.getMyTrainer().getName()+" "+sportsMan.getMyTrainer().getSurname());
        sportsMan.chooseOtherTrainer();
        System.out.println(sportsMan.getMyTrainer().getName()+" "+sportsMan.getMyTrainer().getSurname());

    }


}
