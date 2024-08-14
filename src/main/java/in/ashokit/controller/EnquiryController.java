package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.service.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;

	@Autowired
	private HttpSession httpSession;

	@GetMapping("/logout")
	public String logout() {
		httpSession.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {

		System.out.println("dashboard method called");

		Integer userId = (Integer) httpSession.getAttribute("userId");

		DashboardResponse dashboardData = enquiryService.getDashboardData(userId);

		model.addAttribute("dashboardData", dashboardData);

		return "dashboard";
	}
	
	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {
		
		System.out.println(formObj);
		
		boolean enquiry = enquiryService.addEnquiry(formObj);
		
		if(enquiry) {
			model.addAttribute("successMsg", "Enquiry Added");
		}else {
			model.addAttribute("errMsg", "Problem occure");
		}
		
		return "add-enquiry";
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {

		// get cources for drop down
		List<String> courseName = enquiryService.getCourseName();
		
		// get enq status for drop down
		List<String> enquiryStatus = enquiryService.getEnquiryStatus();
		
//		create binding class obj
		EnquiryForm formObj = new EnquiryForm();
		
//		set data to modelobj
		model.addAttribute("courseName", courseName);
		model.addAttribute("enquiryStatus", enquiryStatus);
		model.addAttribute("formObj", formObj);
		
		return "add-enquiry";
	}

	@GetMapping("/enquiries")
	public String viewEnquiryPage() {
		return "view-enquiries";
	}
}
