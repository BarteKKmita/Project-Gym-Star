package com.learning.gym.star.training;

import com.learning.gym.star.gym.database.jdbc.TestSuite;
import com.learning.gym.star.training.cardio.controller.ControllerIntegrationWithEmbeddedMySQLTest;
import com.learning.gym.star.training.power.controller.PowerControllerIntegrationTestWithEmbeddedMySQL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ControllerIntegrationWithEmbeddedMySQLTest.class,
        PowerControllerIntegrationTestWithEmbeddedMySQL.class
})
public class TestSuiteForTrainings {

    @BeforeClass
    public static void setUpClass(){
        TestSuite.setUpClass();
    }

    @AfterClass
    public static void tearDownClass(){
        TestSuite.tearDownClass();
    }

}
