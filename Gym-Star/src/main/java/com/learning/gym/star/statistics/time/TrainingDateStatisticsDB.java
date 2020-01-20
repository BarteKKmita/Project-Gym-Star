//package com.learning.gym.star.statistics.time;
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//@Entity
//@Table(name = "sportsmentrainingtimestatistics")
//public class TrainingDateStatisticsDB {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String statisticsId;
//
//    @Basic
//    @Temporal(TemporalType.DATE)
//    private LocalDate date;
//
//    @Basic
//    @Temporal(TemporalType.DATE)
//    private LocalTime time;
//
//    @OneToMany()
//    private String sportsmanStatsId;
//}
