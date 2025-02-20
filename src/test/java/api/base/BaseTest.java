package api.base;

import api.utils.Specification;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import api.utils.ListeningConfig;
import api.utils.Specification;

@Listeners(ListeningConfig.class)
public class BaseTest {

    Specification specification = new Specification();

    @BeforeClass
    public void setUp() {
        specification.installSpec();

    }



}
