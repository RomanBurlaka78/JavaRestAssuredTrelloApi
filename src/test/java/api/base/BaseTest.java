package api.base;

import api.utils.ApiClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import api.utils.ListeningConfig;

@Listeners(ListeningConfig.class)
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        ApiClient.getInstance().getRequestSpec();
    }
}
