package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;

public interface EnquiryService {
	
	public List<String> getCourseName();
	
	public List<String> getEnquiryStatus();

	public DashboardResponse getDashboardData(Integer userId);
	
	public boolean addEnquiry(EnquiryForm enquiryForm);
	
	public List<EnquiryForm> getEnquiries(Integer userId,EnquirySearchCriteria criteria);
	
	public EnquiryForm getEnquiry(Integer enqId);
}
