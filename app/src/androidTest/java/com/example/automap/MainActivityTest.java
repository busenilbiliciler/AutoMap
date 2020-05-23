package com.example.automap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainActivityTest {

    MainActivity mainActivity;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception {

        mainActivity = new MainActivity();

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void pass_mailCheck() {

        MainActivity mainActivity = new MainActivity();

        assertEquals("E-mail already exists", mainActivity.pass_mailCheck("bnbiliciler44@gmail.com", "123456", "123456"));
        assertEquals("Registered Successfully", mainActivity.pass_mailCheck("mervedemir@gmail.com", "123456", "123456"));
        assertEquals("Password do not match", mainActivity.pass_mailCheck("baturalpyılmaz@gmail.com", "123446", "123456"));
        assertEquals("Fields are empty", mainActivity.pass_mailCheck("", "", ""));


    }









}





