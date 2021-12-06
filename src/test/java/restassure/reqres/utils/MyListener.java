package restassure.reqres.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import restassure.reqres.BaseTest;

public class MyListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        BaseTest.LOGGER.info("Test start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.LOGGER.info("Test success!");
        Capturer.takeScreenshot();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.LOGGER.info("Test fail...");
    }
}
