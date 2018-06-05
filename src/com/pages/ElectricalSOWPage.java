package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.TestBase;
import com.util.Constants;

public class ElectricalSOWPage extends TestBase {
	WebDriver driver;
	public ElectricalSOWPage(WebDriver dr) {
		driver = dr;
	}
	
// Location Info
	@FindBy(xpath=Constants.el_scope_of_work_tab)
	public WebElement el_scope_of_work_tab;
	@FindBy(xpath=Constants.el_sow_service_work)
	public WebElement el_sow_service_work;
	@FindBy(xpath=Constants.el_sow_general_wiring)
	public WebElement el_sow_general_wiring;
	@FindBy(xpath=Constants.el_sow_lighting_work)
	public WebElement el_sow_lighting_work;
	@FindBy(xpath=Constants.el_sow_lighting_work_type)
	public WebElement el_sow_lighting_work_type;
	

	@FindBy(xpath=Constants.el_ge1_job_description)
	public WebElement el_ge1_job_description;
	@FindBy(xpath=Constants.el_gi_job_start_date_calendar)
	public WebElement el_gi_job_start_date_calendar;
	@FindBy(xpath=Constants.el_gi_calendar_active_day)
	public WebElement el_gi_calendar_active_day;	
	@FindBy(xpath=Constants.el_gi_job_completion_date_calendar)
	public WebElement el_gi_job_completion_date_calendar;
	@FindBy(xpath=Constants.el_gi_job_completion_last_day)
	public WebElement el_gi_job_completion_last_day;
	@FindBy(xpath=Constants.el_gi_job_is_this_joint_venture_yes)
	public WebElement el_gi_job_is_this_joint_venture_yes;
	@FindBy(xpath=Constants.el_gi_job_is_this_joint_venture_no)
	public WebElement el_gi_job_is_this_joint_venture_no;
	@FindBy(xpath=Constants.el_gi_building_use_type)
	public WebElement el_gi_building_use_type;
	
	@FindBy(xpath=Constants.el_add_minor_work_description_button)
	public WebElement el_add_minor_work_description_button;
	@FindBy(xpath=Constants.el_gi_applicant_email)
	public WebElement el_gi_applicant_email;
	@FindBy(xpath=Constants.el_gi_applicant_license_type)
	public WebElement el_gi_applicant_license_type;
	@FindBy(xpath=Constants.el_gi_owner_email)
	public WebElement el_gi_owner_email;	
	@FindBy(xpath=Constants.el_gi_owner_type)
	public WebElement el_gi_owner_type;
	@FindBy(xpath=Constants.el_gi_is_this_build_back_job_no)
	public WebElement el_gi_is_this_build_back_job_no;	

	@FindBy(xpath=Constants.el_boiler_controls_tab)
	public WebElement el_boiler_controls_tab;
	@FindBy(xpath=Constants.el_boiler_controls_quantity)
	public WebElement el_boiler_controls_quantity;
	@FindBy(xpath=Constants.el_boiler_controls_number_size)
	public WebElement el_boiler_controls_number_size;	
	
	@FindBy(xpath=Constants.el_service_meter_equipment)
	public WebElement el_service_meter_equipment;
	@FindBy(xpath=Constants.el_number_3_wire)
	public WebElement el_number_3_wire;
	@FindBy(xpath=Constants.el_number_4_wire)
	public WebElement el_number_4_wire;
	@FindBy(xpath=Constants.el_number_10_points)
	public WebElement el_number_10_points;
	@FindBy(xpath=Constants.el_number_existing_meters)
	public WebElement el_number_existing_meters;
	@FindBy(xpath=Constants.el_number_new_meters)
	public WebElement el_number_new_meters;
	@FindBy(xpath=Constants.el_number_removed_meters)
	public WebElement el_number_removed_meters;
	@FindBy(xpath=Constants.el_category_of_work_slider)
	public WebElement el_category_of_work_slider;
	@FindBy(xpath=Constants.el_select_category_of_work)
	public WebElement el_select_category_of_work;	
	@FindBy(xpath=Constants.el_minor_work_description_slider)
	public WebElement el_minor_work_description_slider;	
	@FindBy(xpath=Constants.el_minor_work_type)
	public WebElement el_minor_work_type;	
	@FindBy(xpath=Constants.el_minor_work_floor)
	public WebElement el_minor_work_floor;	
	@FindBy(xpath=Constants.el_minor_work_device)
	public WebElement el_minor_work_device;	
	
	@FindBy(xpath=Constants.el_minor_work_options)
	public WebElement el_minor_work_options;	
	@FindBy(xpath=Constants.el_minor_work_location)
	public WebElement el_minor_work_location;
	@FindBy(xpath=Constants.el_minor_work_quantity)
	public WebElement el_minor_work_quantity;	
	@FindBy(xpath=Constants.el_minor_work_description)
	public WebElement el_minor_work_description;	
	@FindBy(xpath=Constants.el_minor_work_save_button)
	public WebElement el_minor_work_save_button;
	
	@FindBy(xpath=Constants.el_gi_confirm_save_button)
	public WebElement el_gi_confirm_save_button;
	
	@FindBy(xpath=Constants.preview_to_file_button)
	public WebElement preview_to_file_button;
	@FindBy(xpath=Constants.click_go_next_button)
	public WebElement click_go_next_button;
	@FindBy(xpath=Constants.final_legal_contect_checkbox)
	public WebElement final_legal_contect_checkbox;
	@FindBy(xpath=Constants.file_button)
	public WebElement file_button;
	@FindBy(xpath=Constants.global_cancel_button)
	public WebElement global_cancel_button;
	
// Work Description	
	public void workDescription(String sow) {	
		if(!sow.equals("")){
			System.out.println(convertedTimestamp() + " **************** " + "Scope Of Work Elctrical");
//			loginToPortal(OR_PROPERTIES.getProperty("electrical_user_email"));
			filterJob(OR_PROPERTIES.getProperty("electrical_user_email"),JOB_NUMBER.getProperty("job_number_el"));
			test = rep.startTest("Scope Of Work");
			waitUntilISpinnersInvisible();
			click(Constants.el_scope_of_work_tab);
			waitUntilISpinnersInvisible();
			waitUntilElementVisible(Constants.el_select_category_of_work, 30);
			if(sow.equals("NEW INSTALLATION")) {
				scrollAllWayUp();
				waitUntilElementVisible("//*[@id='toggle-chk1']", 30);
				check(Constants.el_sow_service_work);
				check(Constants.el_sow_general_wiring);
				check(Constants.el_sow_lighting_work);
				type(Constants.el_sow_lighting_work_type, sow);
				scrollAllWayDown();
				wait(2);
				scrollToElement(Constants.el_boiler_controls_tab);
				click(Constants.el_boiler_controls_tab);
				type(Constants.el_boiler_controls_quantity, "1");
				type(Constants.el_boiler_controls_number_size, "2");
				click(Constants.el_boiler_controls_tab);
				click(Constants.el_service_meter_equipment);
//				waitUntilElementVisible(Constants.el_number_3_wire, 30);
				type(Constants.el_number_3_wire, "11");
				type(Constants.el_number_4_wire, "12");
				type(Constants.el_number_10_points, "14");
				type(Constants.el_number_existing_meters, "15");
				type(Constants.el_number_new_meters, "16");
				type(Constants.el_number_removed_meters, "17");
				click(Constants.el_service_meter_equipment);
			}
			if(sow.equals("Minor Work")){
//				select(Constants.el_select_category_of_work, "4");
				select(Constants.el_select_category_of_work, sow);
				type(Constants.el_select_category_of_work, sow);
				click(Constants.global_save_step_button);
				click(Constants.global_notification_ok_button);
				waitInvisible(Constants.global_notification_ok_button);
				waitUntilISpinnersInvisible();
				waitUntilElementVisible(Constants.el_minor_work_description_slider, 30);
				scrollToElement(Constants.el_minor_work_description_slider);
				click(Constants.el_minor_work_description_slider);
				click(Constants.el_add_minor_work_description_button);
				type(Constants.el_minor_work_options, "(E) Repair of Defective Fixtures");
				type(Constants.el_minor_work_location, "Pit");
				type(Constants.el_minor_work_quantity, "1");
				type(Constants.el_minor_work_description, "minor test description");
/*				waitUntilElementVisible(Constants.el_minor_work_options, 30);
				el_minor_work_options.sendKeys("(E) Repair of Defective Fixtures");
				el_minor_work_location.sendKeys("Pit");
				el_minor_work_quantity.sendKeys("1");
				el_minor_work_description.sendKeys("minor test description");*/
				click(Constants.el_minor_work_save_button);
//				el_minor_work_save_button.click();
				click(Constants.global_notification_ok_button);
				waitInvisible(Constants.global_notification_ok_button);
			}
			if(sow.equals("Field Sign Inspection")){
				select(Constants.el_select_category_of_work, sow);
				type(Constants.el_job_number_field, "B00007126");
				click(Constants.electric_sign_description_tab);
				click(Constants.global_save_step_button);
				click(Constants.global_notification_ok_button);
				waitInvisible(Constants.global_notification_ok_button);
				waitUntilISpinnersInvisible();
				click(Constants.add_sign_description_button);
				type(Constants.sign_dimentions, "111");
				type(Constants.total_sq_feet_of_sign, "222");
				type(Constants.total_circuit_in_sign, "333");
				type(Constants.total_lamps, "444");
				type(Constants.total_wats, "555");
				type(Constants.number_of_transformenrs, "777");
				type(Constants.va_of_each_transformer, "888");
				type(Constants.total_wats_of_va, "999");
				type(Constants.avg_gauge_of_wires, "789");
				type(Constants.number_of_sockets_per_circuit, "211");
				type(Constants.material_and_gauge_used, "233");
				type(Constants.make_of_sign, "244");
				type(Constants.manufacturer_of_sign, "IBM");
				type(Constants.manufacturer_address, "100 Main");
				click(Constants.el_save_modal_button);
				click(Constants.global_notification_ok_button);
				waitInvisible(Constants.global_notification_ok_button);
				waitInvisible(Constants.el_save_modal_button);
				waitUntilISpinnersInvisible();
			}
			click(Constants.global_save_step_button);
			waitVisible(Constants.global_notification_ok_button);
// ASSERT NOTIFICATION	
			assertNotification(TEXT_PROPERTIES.getProperty("job_filing_saved"), "job_filing_saved");
/*			waitUntilElementVisible(Constants.global_notification_ok_button, 30);
			if(sow.equals("NEW INSTALLATION"))
				assertNotification(TEXT_PROPERTIES.getProperty("job_filing_saved"), "job_filing_saved");
			if(sow.equals("Minor Work"))
				assertNotification(TEXT_PROPERTIES.getProperty("job_filing_saved"), "job_filing_saved");*/
			click(Constants.global_notification_ok_button);
			waitInvisible(Constants.global_notification_ok_button);
		}
	}
}