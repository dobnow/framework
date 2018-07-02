package com.testcases.elv;

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
import com.base.TestBase;
import com.pages.CrmTaskFormPage;
import com.pages.DobDS1Page;
import com.pages.DobDashboardPage;
import com.pages.DobPW1Page;
import com.pages.DobPW3Page;
import com.pages.DobSOWPage;
import com.pages.DobTR1Page;
import com.pages.DobTR8Page;
import com.pages.ElevatorsGeneralInfoPage;
import com.pages.ElevatorsDevicedetailsPage;
import com.pages.ElevatorsInsurancePage;
import com.pages.ElevatorsDocumentsPage;
import com.pages.ElevatorsSignaturesPage;
import com.pages.TpPage;
import com.relevantcodes.extentreports.LogStatus;

public class Pa extends TestBase {

	Xls_Reader xlsx = new Xls_Reader(Constants.testCasesesAssembly);

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
		return TestUtil.getData("Pa", xlsx);
	}

	@Test(priority = 0, dataProvider = "getTestData", invocationCount = 1)
	public void Portal(Hashtable<String, String> data) {
		if (!TestUtil.isExecutable("Pa", xlsx) || data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		System.out.println("BEGIN " + convertedTimestamp() + " **************** " + data.get("description"));
		test = rep.startTest(data.get("description"));
		test.log(LogStatus.INFO, data.get("description"));
		test = rep.startTest("Test Case Data");
		test.log(LogStatus.INFO, data.toString());
		TpPage tp = new TpPage();
		
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.cpeAssign(data.get("cpe"));
		
		tp.selectWorkTypePa(data.get("work_type"));
		tp.locationImfo(data.get("address"));
		tp.workOnFloors(data.get("work_on_floors"));
		tp.zonning(data.get("work_on_floors"));
		tp.applicantInfo(data.get("user_info"));
		tp.reviewtype(data.get("filing_review_type"));
		tp.ownerinfo(data.get("owner_info"));
		tp.party(data.get("party"));		
		tp.saveGI("Y");
		tp.scopeOfWorkPa(data.get("event_info"));
		tp.techReport(data.get("tech_report"));
		tp.progressInspector(data.get("tech_report"));
		tp.signatures(data.get("signature"));
		tp.owner(data.get("owner"));
		tp.uploadDocuments(data.get("documents"));
		tp.previewToFile(data.get("file"));
		successMessage(data.get("description"));
	}

	// ASSIGN TO TEAM
	@Test(priority = 1, dataProvider = "getTestData", dependsOnMethods = {"Portal"})
	public void CentralAssigner(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.centralAssigner(data.get("cpe_acpe"));
	}

	// CPE ASSIGN TO PE
	@Test(priority = 2, dataProvider = "getTestData", dependsOnMethods = {"CentralAssigner"})
	public void CpeAssign(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.cpeAssign(data.get("cpe"));
	}
	
	// PE 2 ACTION VIEW-ACCEPT DOCS 
	@Test(priority = 3, dataProvider = "getTestData", dependsOnMethods = {"CpeAssign"})
	public void secondaryPeAction(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.viewAcceptDocuments(data.get("cpe"));
	}
	// PE 1 ACTION
	@Test(priority = 4, dataProvider = "getTestData", dependsOnMethods = {"CpeAssign"})
	public void primaryPeAction(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.peAction(data.get("primary_pe"));
	}
	// CPE ACTION
	@Test(priority = 5, dataProvider = "getTestData", dependsOnMethods = {"primaryPeAction","secondaryPeAction"})
	public void CpeAction(Hashtable<String, String> data) {
		CrmTaskFormPage task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		task_form.cpeAction(data.get("chief_plan_examiner"));
	}

}