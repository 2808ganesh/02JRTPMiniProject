package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="AIT_COURSES")
public class CourceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	private String courseName;
	
}
