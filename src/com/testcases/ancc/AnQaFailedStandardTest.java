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
import com.pages.DobPW2Page;
import com.base.TestBase;
import com.pages.CrmTaskFormPage;
import com.pages.DobSOWPage;
import com.pages.DobPW3Page;
import com.pages.DobDS1Page;
import com.pages.DobTR1Page;
import com.pages.DobTR8Page;
import com.pages.DobSignaturesPage;
import com.relevantcodes.extentreports.LogStatus;

public class AnQaFailedStandardTest extends TestBase {
	
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
		killDriver();
	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData("AnQaFailedStandardTest", xlsx);
	}

	@Test(priority = 0, dataProvider = "getTestData")
	public void Base(Hashtable<String, String> data) {
		if (!TestUtil.isExecutable("AnQaFailedStandardTest", xlsx) || data.get("Runmode").equals("N"))
			throw new SkipException("Skipping test");
		System.out.println("BEGIN " + convertedTimestamp() + " **************** " + data.get("description"));
		test = rep.startTest(data.get("description"));
		test.log(LogStatus.INFO, data.get("description"));
		test = rep.startTest("Test Case Data");
		test.log(LogStatus.INFO, data.toString());
		
	}

	// PW1
	@Test(priority = 1, dataProvider = "getTestData", dependsOnMethods = {"Base"})
	public void PW1(Hashtable<String, String> data) {
		if (!data.get("filing_review_type").equals("")) {
			DobDashboardPage dash = PageFactory.initElements(driver, DobDashboardPage.class);
			DobPW1Page pw1 = PageFactory.initElements(driver, DobPW1Page.class);
			dash.selectWorkType(data.get("work_type"));
			pw1.locationImfo(data.get("address"));
			pw1.workOnFloors(data.get("work_on_floors"));
			pw1.applicantInfo(data.get("user_info"));
			pw1.reviewtype(data.get("filing_review_type"));
			pw1.directive14acceptanceRequested(data.get("job_project_type"));
			pw1.workTypes(data.get("new_existing_both"));
			pw1.additionalInfo(data.get("cost_floor_area_build_type"));
			pw1.additionalConciderationsAntenna(data.get("demolition"));
			pw1.additionalConciderationsCurb(data.get("additional_conciderations"));
			pw1.complianceNYCECC(data.get("nycecc"));
			pw1.zonningCharacteristics(data.get("dist_overlay_spec_dist_map"));
			pw1.buildingCharacteristics(data.get("building_charcteristics"));
			pw1.curbCutDescription(data.get("curb_cut_description"));
			pw1.fireProtectionEquipment(data.get("fire_equipment"));
			pw1.siteCharacteristics(data.get("site_characteristics"));
			pw1.savePW1(data.get("save_pw1"));
		}
	}

	// PW1 DEMOLITION SUBMITTAL
	@Test(priority = 2, dataProvider = "getTestData", dependsOnMethods = {"PW1"})
	public void DS1(Hashtable<String, String> data) {
		DobDS1Page ds1 = PageFactory.initElements(driver, DobDS1Page.class);
		ds1.demolitionSubmittal(data.get("ds1"));
	}

	// ASOW ANTENNA SCOPE OF WORK
	@Test(priority = 3, dataProvider = "getTestData", dependsOnMethods = {"DS1"})
	public void ASOW(Hashtable<String, String> data) {
		DobSOWPage asw = PageFactory.initElements(driver, DobSOWPage.class);
		asw.scopeOfWork(data.get("asw"));
	}

	// PW3
	@Test(priority = 4, dataProvider = "getTestData", dependsOnMethods = {"ASOW"})
	public void PW3(Hashtable<String, String> data) {
		DobPW3Page pw3 = PageFactory.initElements(driver, DobPW3Page.class);
		pw3.costAffidavit(data.get("pw3"));
	}

	// TR1 ADD
	@Test(priority = 5, dataProvider = "getTestData", dependsOnMethods = {"PW3"})
	public void TR1Special(Hashtable<String, String> data) {
		DobTR1Page tr1 = PageFactory.initElements(driver, DobTR1Page.class);
		tr1.specialInspection(data.get("tr1"));
	}

	// TR1 SIGN
	@Test(priority = 6, dataProvider = "getTestData", dependsOnMethods = {"TR1Special"})
	public void TR1SpecialSignature(Hashtable<String, String> data) {
		DobTR1Page tr1 = PageFactory.initElements(driver, DobTR1Page.class);
		tr1.specialInspectorSignature(data.get("special_inspector_sign_upload"));
	}

	// TR1 Progress Signature
	@Test(priority = 7, dataProvider = "getTestData", dependsOnMethods = {"TR1SpecialSignature"})
	public void TechnicalReportProgressTest(Hashtable<String, String> data) {
		DobTR1Page tr1 = PageFactory.initElements(driver, DobTR1Page.class);
		tr1.progressInspecSign(data.get("progress_inspector_sign_upload"));
	}

	// TR8 ADD
	@Test(priority = 8, dataProvider = "getTestData", dependsOnMethods = {"TechnicalReportProgressTest"})
	public void TR8Report(Hashtable<String, String> data) {
		DobTR8Page tr8 = PageFactory.initElements(driver, DobTR8Page.class);
		tr8.energyCodeProgressInspection(data.get("tr8"));
	}

	// TR8 Signature
	@Test(priority = 9, dataProvider = "getTestData", dependsOnMethods = {"TR8Report"})
	public void TR8Signature(Hashtable<String, String> data) {
		DobTR8Page tr8 = PageFactory.initElements(driver, DobTR8Page.class);
		tr8.energyCodeSignature(data.get("energy_code_inspector_sign_upload"));
	}

	// STATENTS SIGNATURES
	@Test(priority = 10, dataProvider = "getTestData", dependsOnMethods = {"TR8Signature"})
	public void Signatures(Hashtable<String, String> data) {
		DobSignaturesPage signature = PageFactory.initElements(driver, DobSignaturesPage.class);
		signature.applicantStatementsSignature(data.get("signatures"));
		signature.ownerSignature(data.get("owner_signature"));
	}

	// DOCUMENTS
	@Test(priority = 11, dataProvider = "getTestData", dependsOnMethods = {"Signatures"})
	public void Documents(Hashtable<String, String> data) {
		DobDocumentsPage docs = PageFactory.initElements(driver, DobDocumentsPage.class);
		docs.uploadDocuments(data.get("documents"));
	}

	// PREVIEW TO FILE
	@Test(priority = 12, dataProvider = "getTestData", dependsOnMethods = {"Documents"})
	public void PreviewToFile(Hashtable<String, String> data) {
		DobPW1Page pw1 = PageFactory.initElements(driver, DobPW1Page.class);
		pw1.previewToFile(data.get("preview_to_file"));
	}

	// CPE VIEW-ACCEPT DOCS
	@Test(priority = 13, dataProvider = "getTestData", dependsOnMethods = {"PreviewToFile"})
	public void CPEAcceptDocsTest(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.viewAcceptDocuments(data.get("chief_plan_examiner"));
	}

	// CPE ASSIGN
	@Test(priority = 14, dataProvider = "getTestData", dependsOnMethods = {"CPEAcceptDocsTest"})
	public void ChiefPlanExaminerTest(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.cpeAction(data.get("chief_plan_examiner"));
	}

	// PE ACTION
	@Test(priority = 15, dataProvider = "getTestData", dependsOnMethods = {"ChiefPlanExaminerTest"})
	public void PlanExaminerTest(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.peAction(data.get("plan_examiner"));
	}

	// PW2-2
	@Test(priority = 16, dataProvider = "getTestData", dependsOnMethods = {"PlanExaminerTest"})
	public void WorkPermit2Test(Hashtable<String, String> data) {
		DobPW2Page pw2 = PageFactory.initElements(driver, DobPW2Page.class);
		pw2.workPermit(data.get("pw2_2"));
		pw2.uploadDocuments(data.get("pw2_2_documents"));
	}

	// CRM QA SUPERVISER
	@Test(priority = 17, dataProvider = "getTestData", dependsOnMethods={ "WorkPermit2Test"})
	public void QaSuperviserTest(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.assignTo(data.get("qa_superviser"));
	}

	// QA FAILED
	@Test(priority = 18, dataProvider = "getTestData", dependsOnMethods={ "QaSuperviserTest"})
	public void QaFailedTest(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.qaFailed(data.get("qa_administrator"));
		successMessage(data.get("description"));
	}

}