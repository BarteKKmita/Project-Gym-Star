package com.learning.gym.star.gym;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DatabaseTest {

    @Resource
    private EntityManager entityManager;

    @Test
    public void testDatabase () {

        Query query1 = entityManager.createNativeQuery("SELECT * FROM gym");
        // ResultSet set = (ResultSet) query1.getSingleResult();
        System.out.println(Arrays.toString((Object[]) query1.getSingleResult()));
        //assertEquals(BigInteger.valueOf(1L), query1.getSingleResult());
    }
}