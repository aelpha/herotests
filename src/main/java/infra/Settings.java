package infra;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@ToString
public class Settings {

    @Getter
    private static boolean isLocalBrowser;
    @Getter private static String serviceSlug;
    @Getter private static HttpLoggingInterceptor.Level logLevel;
    @Getter private static String commitHash;
    //@Getter private static Service service;
    @Getter private static int testRetries;
    @Getter private static boolean isAppiumLocal;
    @Getter private static String appiumServiceIpAddress;
    @Getter private static String appiumBasePath;
    @Getter private static String appiumLocalUdid;
    @Getter private static String appiumLocalPlatformVersion;
    @Getter private static String appiumLocalDeviceId;
    @Getter private static int appiumProxyPort;

    private static final String PROPERTIES_FILE_PATH = "test.properties";

    static {
        init();
    }

    private static void init() {
        log.info("Init Settings...");
        Properties prop = getPropertiesFile();
        isLocalBrowser = Boolean.parseBoolean(prop.getProperty("test.is_local_browser", "false"));
        testRetries = Integer.parseInt(prop.getProperty("test.retries", "2"));

        if (System.getProperty("test.ci_project_slug") != null
                && !System.getProperty("test.ci_project_slug").isEmpty()) {
            serviceSlug = System.getProperty("test.ci_project_slug");
        } else {
            serviceSlug = prop.getProperty("test.slug");
        }

        logLevel = HttpLoggingInterceptor.Level.valueOf(prop.getProperty("test.log_level", "BASIC"));
        commitHash = System.getProperty("test.ci_commit_short", "");
        //service = Service.fromString(serviceSlug);

        isAppiumLocal = Boolean.parseBoolean(prop.getProperty("appium.is_local", "false"));
        appiumServiceIpAddress = "127.0.0.1";
        appiumBasePath = prop.getProperty("appium.service.base_path", "/wd/hub");
        appiumLocalUdid = prop.getProperty("appium.local.udid", "4B275C22-008C-445F-BDC1-6961C09C8EB0");
        appiumLocalPlatformVersion = prop.getProperty("appium.local.platform_version", "16.0");
        appiumProxyPort = Integer.parseInt(prop.getProperty("appium.proxy.port", "8888"));
        appiumLocalDeviceId =
                prop.getProperty("appium.local.device_id", "C2378120-849E-47F8-8226-92FB6B56933D");

        log.info(
                "Settings for run:\n>isLocalBrowser={}\n>serviceSlug={}\n>commitHash={}\nisAppiumLocal={}",
                isLocalBrowser,
                serviceSlug,
                commitHash,
                isAppiumLocal);
        //log.info("Settings for service: {}", service.toString());
        log.info(
                "Settings for Appium:\n>ipAddress={}\n>basePath={}\nlocalUdid={}\nlocalPlatformVersion={}\ndeviceId={}\nproxyPort={}",
                appiumServiceIpAddress,
                appiumBasePath,
                appiumLocalUdid,
                appiumLocalPlatformVersion,
                appiumLocalDeviceId,
                appiumProxyPort);
    }

    private static Properties getPropertiesFile() {
        Properties prop = new Properties();
        if (new File(PROPERTIES_FILE_PATH).exists()) {
            log.info("Get properties from test.properties");
            try (InputStream input = new FileInputStream(PROPERTIES_FILE_PATH)) {
                prop.load(input);
            } catch (Exception e) {
                throw new ExceptionWrapper("Something wrong with test.properties");
            }
        }
        return prop;
    }
}