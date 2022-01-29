package com.automation.test;

import com.automation.develop.base.BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class setupDockerSeleniumHub extends BaseClass {

    @Test
    public void setupDocker () throws IOException, InterruptedException {
        startDocker();

    }
    @Test()
    public void tearDownDocker () throws IOException, InterruptedException {
        stopDocker();
    }





}
