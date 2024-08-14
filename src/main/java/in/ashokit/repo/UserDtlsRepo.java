package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.UserDtlsEntity;
import java.util.List;


@Repository
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {

	public UserDtlsEntity findByEmail(String email);
	
	public UserDtlsEntity findByEmailAndPwd(String email,String pwd);
}
