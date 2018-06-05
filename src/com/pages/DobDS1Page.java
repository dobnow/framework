package com.pages;

import com.base.TestBase;
import com.util.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DobDS1Page extends TestBase {
	WebDriver driver;
	public DobDS1Page(WebDriver dr) {
		driver = dr;
	}

	@FindBy(xpath=Constants.ds1_demolition_submittal_step)
	public WebElement ds1_demolition_submittal_step;
	@FindBy(xpath=Constants.ds1_3B_description)
	public WebElement ds1_3B_description;	
	@FindBy(xpath=Constants.ds1_3B_i_prepared_demolition_documents)
	public WebElement ds1_3B_i_prepared_demolition_documents;
	@FindBy(xpath=Constants.global_save_step_button)
	public WebElement global_save_step_button;
	@FindBy(xpath=Constants.global_notification_ok_button)
	public WebElement global_notification_ok_button;
	@FindBy(xpath=Constants.global_loading_spinner)
	public WebElement global_loading_spinner;
	
	public static String todaydate;
	
	public void demolitionSubmittal(String ds1) {	
		if(ds1.equals("Y")){
			System.out.println(convertedTimestamp() + " **************** " + "DS1 DemolitionSubmittalTest");
//			loginToPortal(OR_PROPERTIES.getProperty("user_email"));
			filterJob(OR_PROPERTIES.getProperty("user_email"));	
			test = rep.startTest("Demolition");
			waitUntilElementVisible(Constants.ds1_demolition_submittal_step, 30);
			ds1_demolition_submittal_step.click();
			waitUntilElementVisible(Constants.ds1_3B_description, 30);
			ds1_3B_description.sendKeys("smoke test " + new SimpleDateFormat("MM/dd/yy HH-mm").format(Calendar.getInstance().getTime()));
			waitUntilElementVisible(Constants.ds1_3B_i_prepared_demolition_documents, 30);
			ds1_3B_i_prepared_demolition_documents.click();
			waitUntilElementVisible(Constants.global_save_step_button, 30);
			global_save_step_button.click();
			waitUntilElementVisible(Constants.global_notification_ok_button, 30);
			assertNotification(TEXT_PROPERTIES.getProperty("job_filing_saved"), "job_filing_saved");
			click(Constants.global_notification_ok_button);
//			click(Constants.global_notification_ok_button);
//			waitUntilElementVisible(Constants.global_loading_spinner, 10);
			waitInvisible(Constants.global_loading_spinner);
		}
	}
}