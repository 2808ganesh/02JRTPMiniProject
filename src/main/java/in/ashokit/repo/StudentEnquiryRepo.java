package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.StudentEnquiryEntity;

@Repository
public interface StudentEnquiryRepo extends JpaRepository<StudentEnquiryEntity,Integer> {

}
