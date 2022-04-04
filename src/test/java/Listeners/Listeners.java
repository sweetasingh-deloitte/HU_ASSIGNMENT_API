/*package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

    public static ExtentHtmlReporter HtmlReporter;

    public static ExtentReports Extent;
    public static ExtentTest logger;

    @Override

    public void onStart(ITestContext iTestContext) {

        HtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReport/TodoReport.html");

        HtmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");

        Extent=new ExtentReports();
        Extent.attachReporter(HtmlReporter);

        HtmlReporter.config().setReportName("TODO APP");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {

        logger = Extent.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger = Extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
    }


    @Override
    public void onFinish(ITestContext iTestContext) {
        Extent.flush();
    }
}*/