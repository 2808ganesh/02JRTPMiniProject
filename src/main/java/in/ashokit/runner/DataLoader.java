package in.ashokit.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CourceEntity;
import in.ashokit.entity.EnquiryStausEntity;
import in.ashokit.repo.CourceRepo;
import in.ashokit.repo.EnquiryStatusRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CourceRepo courceRepo;
	
	@Autowired
	private EnquiryStatusRepo enquiryStatusRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

//		List<String> courseList = Arrays.asList("Java","Python","DevOps","AWS");
		
//		List<String> statusList = Arrays.asList("New","Enrolled","Lost");
		
		courceRepo.deleteAll();
		
		CourceEntity c1 = new CourceEntity();
//		c1.setCourseId(1);
		c1.setCourseName("Java");
//		courceRepo.save(c1);

		CourceEntity c2 = new CourceEntity();
//		c1.setCourseId(2);
		c2.setCourseName("Python");
//		courceRepo.save(c2);

		CourceEntity c3 = new CourceEntity();
//		c1.setCourseId(3);
		c3.setCourseName("DevOps");
//		courceRepo.save(c3);

		CourceEntity c4 = new CourceEntity();
//		c1.setCourseId(4);
		c4.setCourseName("AWS");
//		courceRepo.save(c4);
		
		List<CourceEntity> list2 = Arrays.asList(c1,c2,c3,c4);
		courceRepo.saveAll(list2);
		
		//  //  //////// ///
		
		enquiryStatusRepo.deleteAll();
		
		EnquiryStausEntity e1 = new EnquiryStausEntity();
//		e1.setStatusId(1);
		e1.setStatusName("New");
//		enquiryStatusRepo.save(e1);

		EnquiryStausEntity e2 = new EnquiryStausEntity();
//		e1.setStatusId(2);
		e2.setStatusName("Enrolled");
//		enquiryStatusRepo.save(e2);

		EnquiryStausEntity e3 = new EnquiryStausEntity();
//		e1.setStatusId(3);
		e3.setStatusName("Lost");
//		enquiryStatusRepo.save(e3);
		
		List<EnquiryStausEntity> list = Arrays.asList(e1,e2,e3);
		
		enquiryStatusRepo.saveAll(list);
		
		
	}

}
