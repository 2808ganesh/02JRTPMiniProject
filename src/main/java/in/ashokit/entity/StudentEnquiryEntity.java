package in.ashokit.entity;
//
//import java.time.LocalDate;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name="AIT_STUDENT_ENQUIRIES")
//public class StudentEnquiryEntity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer enquiryId;
//	private String studentName;
//	private Long phno;
//	private String classMode;
//	private String courseName;
//	private String enquiryStatus;
//	private LocalDate createdDate;
//	private LocalDate updatedDate;
//	private Integer userId;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id") // This column will store the foreign key
//    private UserDtlsEntity user;
//	
	
	//package in.ashokit.entity;

	import java.time.LocalDate;
	import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

	@Data
	@Entity
	@Table(name = "AIT_STUDENT_ENQUIRIES")
	public class StudentEnquiryEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer enquiryId;
	    private String studentName;
	    private Long phno;
	    private String classMode;
	    private String courseName;
//	    private String enquiryStatus;
	    private String statusName;
	    @CreationTimestamp
	    private LocalDate createdDate;
	    @UpdateTimestamp
	    private LocalDate updatedDate;
//	    private Integer userId;

//	    @ManyToOne(fetch = FetchType.LAZY)
//	    @JoinColumn(name = "user_id", referencedColumnName = "userId")
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private UserDtlsEntity user;
	}


