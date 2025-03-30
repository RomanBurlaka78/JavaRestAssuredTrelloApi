package api.base;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import api.utils.ListeningConfig;

import java.util.Arrays;

@Listeners(ListeningConfig.class)
public class BaseTest  {
    ITestContext context;
    @BeforeMethod
    public void skipIfNotProd(ITestContext context) {
        // Проверяем, что это CI-окружение (GitHub Actions, Jenkins и др.)
        boolean isCI = System.getenv("CI") != null ||
                System.getenv("BUILD_NUMBER") != null ||
                System.getenv("GITHUB_ACTIONS") != null;

        // Если это CI, но не prod - пропускаем тест
        if (isCI && !"prod".equals(System.getProperty("env"))) {
            throw new SkipException("Тест работает только в prod. Текущее окружение: " +
                    System.getProperty("env", "не задано"));
        }

        // Если это локальный запуск - разрешаем выполнение без проверок
        System.out.println("Запуск теста: " + context.getCurrentXmlTest().getName() +
                " | Окружение: " + (isCI ? "CI" : "локальное"));
    }




}
