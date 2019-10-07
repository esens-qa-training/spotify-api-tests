package com.esens.automation.api.spotify;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/com/esens/automation/api/spotify/test_api_spotify.feature"},
        plugin = {"pretty"}
)
public class RunCucumberTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunCucumberTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}

