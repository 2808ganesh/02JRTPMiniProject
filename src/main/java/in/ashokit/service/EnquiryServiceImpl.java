package in.ashokit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.entity.CourceEntity;
import in.ashokit.entity.EnquiryStausEntity;
import in.ashokit.entity.StudentEnquiryEntity;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.CourceRepo;
import in.ashokit.repo.EnquiryStatusRepo;
import in.ashokit.repo.StudentEnquiryRepo;
import in.ashokit.repo.UserDtlsRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo dtlsRepo;

	@Autowired
	private CourceRepo courceRepo;

	@Autowired
	private EnquiryStatusRepo enquiryStatusRepo;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private StudentEnquiryRepo studentEnquiryRepo;

	@Override
	public List<String> getCourseName() {
		
		List<CourceEntity> findAll = courceRepo.findAll();

		ArrayList<String> entity = new ArrayList<>();

		for (CourceEntity name : findAll) {
			entity.add(name.getCourseName());
		}
		return entity;
	}

	@Override
	public List<String> getEnquiryStatus() {

		List<EnquiryStausEntity> findAll = enquiryStatusRepo.findAll();

		ArrayList<String> entity = new ArrayList<>();

		for (EnquiryStausEntity statuses : findAll) {

			entity.add(statuses.getStatusName());
		}
		return entity;
	}

	@Override
	public DashboardResponse getDashboardData(Integer userId) {

		DashboardResponse response = new DashboardResponse();

		Optional<UserDtlsEntity> findById = dtlsRepo.findById(userId);

		if (findById.isPresent()) {
			UserDtlsEntity entity = findById.get();
			List<StudentEnquiryEntity> enquiries = entity.getEnquiries();

			int totalCnt = enquiries.size();

			int enrolledCnt = enquiries.stream().filter(e -> e.getStatusName().equals("Enrolled"))
					.collect(Collectors.toList()).size();

			int lostCnt = enquiries.stream().filter(e -> e.getStatusName().equals("Lost"))
					.collect(Collectors.toList()).size();

			response.setTotalEnquiriesCnt(totalCnt);
			response.setEnrolledCnt(enrolledCnt);
			response.setLostCnt(lostCnt);
		}

		return response;
	}

	@Override
	public boolean addEnquiry(EnquiryForm enquiryForm) {

		StudentEnquiryEntity studentEnquiryEntity = new StudentEnquiryEntity();
		
		BeanUtils.copyProperties(enquiryForm, studentEnquiryEntity);
		
		Integer userId = (Integer)  httpSession.getAttribute("userId");
		
		UserDtlsEntity userDtlsEntity = dtlsRepo.findById(userId).get();
		
		studentEnquiryEntity.setUser(userDtlsEntity);
		
		studentEnquiryRepo.save(studentEnquiryEntity);
		
		return true;
	}

	@Override
	public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnquiryForm getEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

}
