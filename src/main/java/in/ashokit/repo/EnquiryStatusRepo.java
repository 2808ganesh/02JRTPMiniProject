package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.EnquiryStausEntity;

@Repository
public interface EnquiryStatusRepo extends JpaRepository<EnquiryStausEntity, Integer> {

}
