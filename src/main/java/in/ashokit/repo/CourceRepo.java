package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.CourceEntity;

@Repository
public interface CourceRepo extends JpaRepository<CourceEntity, Integer>{

	
}
