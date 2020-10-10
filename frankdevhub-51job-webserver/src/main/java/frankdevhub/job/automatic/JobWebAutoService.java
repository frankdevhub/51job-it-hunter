package frankdevhub.job.automatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import frankdevhub.job.automatic.web.pages.JobPlatformSearchPage;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = { "frankdevhub.job.automatic" })
@MapperScan(basePackages = "frankdevhub.job.automatic.mapper")
@EnableTransactionManagement
public class JobWebAutoService {
	public static void main(String[] args) {
		SpringApplication.run(JobWebAutoService.class, args);
		//Build Error:
		/***
		 * Exception in thread "restartedMain" java.lang.reflect.InvocationTargetException
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49)
	Caused by: java.lang.NoClassDefFoundError: org/openqa/selenium/logging/LoggingHandler
		at frankdevhub.job.automatic.selenium.config.DriverType$2.getWebDriverObject(DriverType.java:59)
		at frankdevhub.job.automatic.selenium.config.DriverFactory.instantiateWebDriver(DriverFactory.java:69)
		at frankdevhub.job.automatic.selenium.config.DriverFactory.getDriver(DriverFactory.java:41)
		at frankdevhub.job.automatic.selenium.DriverBase.getDriver(DriverBase.java:38)
		at frankdevhub.job.automatic.web.pages.BaseWebPage.initDriver(BaseWebPage.java:62)
		at frankdevhub.job.automatic.web.pages.BaseWebPage.<init>(BaseWebPage.java:23)
		at frankdevhub.job.automatic.web.pages.JobPlatformSearchPage.<init>(JobPlatformSearchPage.java:59)
		at frankdevhub.job.automatic.JobWebAutoService.main(JobWebAutoService.java:20)
		... 5 more
	Caused by: java.lang.ClassNotFoundException: org.openqa.selenium.logging.LoggingHandler
		at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
		at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
		at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
		at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
		... 13 more

		 *****/
		
		// debug only
		try {
			new JobPlatformSearchPage(false, "java").startSearchResultPatrol();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
