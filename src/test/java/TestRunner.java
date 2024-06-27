import com.aventstack.extentreports.ExtentReports;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.ExtentReportManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Lokasi direktori fitur
        glue = {"stepDefinitions"}, // Paket tempat step definitions berada
        plugin = {"pretty", "html:target/cucumber-reports"} // Plugin untuk laporan Cucumber
)
public class TestRunner {

    private static ExtentReports extent;

    @BeforeClass
    public static void setup() {
        extent = ExtentReportManager.getInstance();
    }

    @AfterClass
    public static void tearDown() {
        extent.flush();
    }
}