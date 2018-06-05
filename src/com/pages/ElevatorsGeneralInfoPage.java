package com.pages;

import com.base.TestBase;
import com.util.Constants;

public class ElevatorsGeneralInfoPage extends TestBase {

//	public String pagesource = driver.getPageSource();

	public void generalInfo(String address) {
		if (!address.equals("")) {
			System.out.println(convertedTimestamp() + " **************** generalInfo");
			test = rep.startTest("General Info");
			String[] data = address.split(" :: ");
			type(Constants.pw1_1_house_number, data[0]);
			type(Constants.pw1_1_street_name, data[1]);
			select(Constants.pw1_1_borough, data[2]);
			type(Constants.pw1_1_block, data[3]);
			type(Constants.pw1_1_lot, data[4]);
			select(Constants.building_code, "2014 ");
			email(OR_PROPERTIES.getProperty("elevator_applicant_email"));
			select(Constants.pw1_2_license_type, OR_PROPERTIES.getProperty("elevator_applicant_lisence_type"));
			select(Constants.select_business, "DIR BUS NAME");
			for (int i = 1; i < 100; i++) {
				clear(Constants.designer_email);
				type(Constants.designer_email, OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase());
				wait(1);
				if (count(Constants.email_xpath_part1
						+ OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase()
						+ Constants.email_xpath_part2) > 0) {
					click(Constants.email_xpath_part1
							+ OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase()
							+ Constants.email_xpath_part2);
					break;
				}
			}
			keyPressTab(Constants.designer_email);
			select(Constants.designer_license_type, OR_PROPERTIES.getProperty("professional_engineer"));
			for (int i = 1; i < 100; i++) {
				clear(Constants.owner_email);
				type(Constants.owner_email, OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase());
				wait(1);
				if (count(Constants.email_xpath_part1+OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase()
						+ Constants.email_xpath_part2) > 0) {
					click(Constants.email_xpath_part1+OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase()
							+ Constants.email_xpath_part2);
					break;
				}
			}
			keyPressTab(Constants.owner_email);
			select(Constants.owner_license_type, OR_PROPERTIES.getProperty("private_tax_exempt"));
			radio("//input[@name='rdScopeOfWorkRegualtionSnycDep'][@value='1']");
			scrollAllWayUp();
			scrollToElement(Constants.global_save_step_button);
			click(Constants.global_save_step_button);
			if (count(Constants.pw1_confirm_save_button) > 0)
				click(Constants.pw1_confirm_save_button);
			waitUntilISpinnersInvisible();
			waitVisible(Constants.ok_button);
			verifyNotification(Constants.notification,TEXT_PROPERTIES.getProperty("applications_created_successfully"));
			clickButton("OK");
			waitInvisible(Constants.ok_button);
			addToProps("job_number", text(Constants.elevator_job_label).substring(1, 10).trim());
			type(Constants.electrical_permit_number, "M368223");
			wait(2);
			click(Constants.global_save_step_button);
			waitUntilISpinnersInvisible();
			waitVisible(Constants.ok_button);
			verifyNotification(Constants.notification, TEXT_PROPERTIES.getProperty("job_filing_saved"));
			clickButton("OK");
			waitInvisible(Constants.ok_button);
		}
	}

	public void searchAddDevice(String address) {
		if (!address.equals("")) {
			System.out.println(convertedTimestamp() + " **************** " + "generalInfoAlteration");
			test = rep.startTest("General Info generalInfoAlteration");
			String[] data = address.split(" :: ");
			radio(Constants.search_by_address);
			click(Constants.search_and_add_button);
			waitVisible(Constants.ok_button);
			clickButton("OK");
			waitInvisible(Constants.ok_button);
			for (int i = 1; i < 100; i++) {
				for (int a = 1; a < 100; a++) {
					String new_address = String.valueOf(randomNumberOf(777));
					clear(Constants.search_by_house);
					type(Constants.search_by_house, new_address);
					clear(Constants.search_by_street);
					type(Constants.search_by_street, data[1]);
					select(Constants.search_by_borough, data[2]);
					wait(1);
					click(Constants.search_and_add_button);
					wait(3);
					if (count(Constants.ok_button) > 0)
						clickButton("OK");
					if (count("//p[@id='desc'][contains(.,'Cannot find BIN for the address entered.')]") > 0)
						clickButton("OK");
					if (count(Constants.device_list_checkbox) > 0)
						break;
				}
				for (int a = 1; a < count(Constants.device_list_checkbox); a++) {
					String circle_xpath = "(//button[contains(@class,'btn-circle2')])[" + a + "]";
					String checkbox_xpath = "(//input[@ng-model='device.IsSelected'])[" + a + "]";
					///                                                                                 copyValueOf(data, offset, count)
					
					//button[@class='btn-circle2 pull-right devActive']
					//button class="btn-circle2 pull-right devRemoved
					
//					System.out.println(count(circle_xpath));
//					click(checkbox_xpath);
					
//					System.out.println(getElement(circle_xpath).getCssValue("background-color"));
					if (getElement(circle_xpath).getCssValue("background-color").contains("rgba(98, 152, 40, 1)"))
						click(checkbox_xpath);
					
					if (count("//p[@id='desc'][contains(.,'You cannot select this device')]") > 0)
						clickButton("OK");
					wait(1);
					if (count(Constants.cannot_use_device_message) > 0)
						clickButton("OK");
					if (count(Constants.add_device_button_disabled) > 0) // ADD button still disabled
						wait(2);
					if (count(Constants.cannot_use_device_message) > 0)
						clickButton("OK");
					if (count(Constants.add_device_button_disabled) == 0)
						click(Constants.add_device_button);
					wait(1);
					if (count(Constants.device_added_message) > 0)
						break;
				}
				wait(2);
				if (count(Constants.device_added_message) > 0) {
					clickButton("OK");
					waitInvisible(Constants.ok_button);

					break;
				} else
					click(Constants.global_cancel_button);
			}
			select(Constants.building_code, "2014 ");
			email(OR_PROPERTIES.getProperty("elevator_applicant_email"));
			select(Constants.pw1_2_license_type, OR_PROPERTIES.getProperty("elevator_applicant_lisence_type"));
			select(Constants.select_business, "DIR BUS NAME");
			for (int i = 1; i < 100; i++) {
				send(Constants.designer_email, OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase());
				wait(1);
				if (count(Constants.email_xpath_part1
						+ OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase()
						+ Constants.email_xpath_part2) > 0) {
					click(Constants.email_xpath_part1 + OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase() + Constants.email_xpath_part2);
					wait(1);
					break;
				}
			}
			select(Constants.designer_license_type, OR_PROPERTIES.getProperty("professional_engineer"));
			for (int i = 1; i < 100; i++) {
				send(Constants.owner_email, OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase());
				wait(1);
				if (count(Constants.email_xpath_part1
						+ OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase()
						+ Constants.email_xpath_part2) > 0) {
					click(Constants.email_xpath_part1 + OR_PROPERTIES.getProperty("elevator_applicant_email").toUpperCase() + Constants.email_xpath_part2);
					wait(1);
					break;
				}
			}
			select(Constants.owner_license_type, OR_PROPERTIES.getProperty("private_tax_exempt"));
			radio("//input[@name='rdScopeOfWorkRegualtionSnycDep'][@value='1']");
			scrollAllWayUp();
			scrollToElement(Constants.global_save_step_button);
			click(Constants.global_save_step_button);
			if (count(Constants.pw1_confirm_save_button) > 0)
				click(Constants.pw1_confirm_save_button);
			waitUntilISpinnersInvisible();
			waitVisible(Constants.ok_button);
			verifyNotification(Constants.notification,
					TEXT_PROPERTIES.getProperty("applications_created_successfully"));
			clickButton("OK");
			waitInvisible(Constants.ok_button);
			addToProps("job_number", text(Constants.elevator_job_label).substring(1, 10).trim());
			// type(Constants.electrical_permit_number, "M368223");
			wait(2);
			click(Constants.global_save_step_button);
			waitUntilISpinnersInvisible();
			waitVisible(Constants.ok_button);
			verifyNotification(Constants.notification, TEXT_PROPERTIES.getProperty("job_filing_saved"));
			clickButton("OK");
			waitInvisible(Constants.ok_button);
		}
	}

	// Preview to File
	public void previewToFile(String preview_to_file) {
		if (!preview_to_file.equals("")) {
			System.out.println(convertedTimestamp() + " **************** " + "Preview ToFile");
			filterJob(OR_PROPERTIES.getProperty("elevator_applicant_email"));
			test = rep.startTest("Preview To File");
			for (int i = 1; i <= 20; i++) {
				click(Constants.preview_resubmit_button);
				waitUntilISpinnersInvisible();
				wait(2);
				if (count("//label[@id='lblUsrMessage'][text()='Getting Preview... 0%']") > 0) {
					click(Constants.global_cancel_button);
					waitInvisible(Constants.global_cancel_button);
					wait(1);
					click(Constants.preview_resubmit_button);
					waitUntilISpinnersInvisible();
					wait(2);
				}
				if (count(Constants.number_of_pages_label) > 0)
					break;
			}
			for (int a = 1; a <= 50; a++) {
				click(Constants.click_go_next_button);
				wait(1);
				if (count(Constants.final_legal_contect_checkbox) > 0)
					break;

			}
			check(Constants.final_legal_contect_checkbox);
			click(Constants.file_button);
			waitInvisible(Constants.file_button);
			waitVisible(Constants.ok_button);
			verifyNotification(Constants.notification, TEXT_PROPERTIES.getProperty("filing_message"));
			clickButton("OK");
			waitInvisible(Constants.ok_button);
			/*
			 * assertFilingStatus(TEXT_PROPERTIES.getProperty("pending_pe_assignment"));
			 * assertFilingStatus(preview_to_file);
			 */
			assertFilingStatus(TEXT_PROPERTIES.getProperty(preview_to_file));
		}
	}
	

	
 
	
	
}