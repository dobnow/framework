package com.testcases.ancc;

import com.util.Constants;
import com.util.TestUtil;
import com.util.Xls_Reader;

import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.support.PageFactory;
import com.pages.DobDashboardPage;
import com.pages.DobDocumentsPage;
import com.pages.DobPW1Page;
import com.base.TestBase;
import com.pages.CrmTaskFormPage;
import com.pages.DobSignaturesPage;
import com.relevantcodes.extentreports.LogStatus;

public class CcOnCcSubsTest extends TestBase {
	
	Xls_Reader xlsx = new Xls_Reader(Constants.testCases);
	
	@BeforeSuite
	public void BeforeSuite() {
		initConfigurations();
	}

	@BeforeMethod
	public void init() {
		initDriver();
		getEnvironmentDetails();
	}

	@AfterMethod
	public void quit() {
		quitDriver();
	}

	@AfterSuite
	public void killDrivers() {
		quitDriver();
		killDriver();
	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData("CcOnCcSubsTest", xlsx);
	}
	
	@Test(priority = 0, dataProvider = "getTestData")
	public void Base(Hashtable<String, String> data) {
		if (!TestUtil.isExecutable("CcOnCcSubsTest", xlsx) || data.get("Runmode").equals("N"))
			throw new SkipException("Skipping test");
		System.out.println("BEGIN " + convertedTimestamp() + " **************** " + data.get("description"));
		test = rep.startTest(data.get("description"));
		test.log(LogStatus.INFO, data.get("description"));
		test = rep.startTest("Test Case Data");
		test.log(LogStatus.INFO, data.toString());

	}
 	
	@Test(priority = 1, dataProvider = "getTestData", dependsOnMethods = {"Base"})
	public void PW1(Hashtable<String, String> data) {
		DobDashboardPage dash = PageFactory.initElements(driver, DobDashboardPage.class);
		DobPW1Page pw1 = PageFactory.initElements(driver, DobPW1Page.class);
		dash.subsFilingAction(data.get("filter"));
		dash.selectWorkTypeSubs(data.get("work_type_subs"));
		waitUntilISpinnersInvisible();
		pw1.applicantInfoSubs(data.get("subsequent_filing"));
		pw1.directive14acceptanceRequested(data.get("job_project_type"));
		pw1.additionalInfoSubs(data.get("cost_floor_area_build_type"));
		pw1.additionalConciderationsCurb(data.get("additional_conciderations"));
		type(Constants.job_description_for_new_work, data.get("description") + " " + convertedTimestamp());
		pw1.zonningCharacteristics(data.get("dist_overlay_spec_dist_map"));
		pw1.buildingCharacteristics(data.get("building_charcteristics"));
		pw1.savePW1_subs(data.get("pw1_subs"));
		pw1.curbCutDescription(data.get("curb_cut_description"));
		pw1.fireProtectionEquipment(data.get("fire_equipment"));
		pw1.siteCharacteristics(data.get("site_characteristics"));
		pw1.savePW1(data.get("pw1_subs")); 
	}

	// STATENTS SIGNATURES
	@Test(priority = 2, dataProvider = "getTestData", dependsOnMethods = {"PW1"})
	public void StatementsSignaturesTest(Hashtable<String, String> data) {
		DobSignaturesPage signature = PageFactory.initElements(driver, DobSignaturesPage.class);
		signature.applicantStatementsSignature(data.get("signatures"));
		signature.ownerSignature(data.get("owner_signature"));
	}

	// DOCUMENTS
	@Test(priority = 9, dataProvider = "getTestData", dependsOnMethods = {"StatementsSignaturesTest"})
	public void DocumentsTest(Hashtable<String, String> data) {
		DobDocumentsPage docs = PageFactory.initElements(driver, DobDocumentsPage.class);
		docs.uploadDocuments(data.get("documents"));
	}

	// PREVIEW TO FILE
	@Test(priority = 10, dataProvider = "getTestData", dependsOnMethods = {"DocumentsTest"})
	public void PreviewToFileTest(Hashtable<String, String> data) {
		DobPW1Page pw1 = PageFactory.initElements(driver, DobPW1Page.class);
		pw1.previewToFile(data.get("preview_to_file"));
	}
// CPE VIEW-ACCEPT DOCS
	@Test(priority=11, dataProvider="getTestData", dependsOnMethods={"PreviewToFileTest"})
	public void CPEAcceptDocsTest(Hashtable<String,String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.viewAcceptDocuments(data.get("chief_plan_examiner"));
	}
	  // CPE ASSIGN TO PE
 	@Test(priority=12, dataProvider="getTestData", dependsOnMethods={"CPEAcceptDocsTest"})
	public void ChiefPlanExaminerTest(Hashtable<String,String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.cpeAction(data.get("chief_plan_examiner"));
	}
	
//	APPROVED
	@Test(priority = 13, dataProvider = "getTestData", dependsOnMethods = {"ChiefPlanExaminerTest"})//
	public void ChiefPlanExaminerApproveSubsTest(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.peAction(data.get("plan_examiner"));
		successMessage(data.get("description"));
	}

}