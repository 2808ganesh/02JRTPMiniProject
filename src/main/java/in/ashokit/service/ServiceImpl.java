package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.UserDtlsRepo;
import in.ashokit.util.EmailUtils;
import in.ashokit.util.PwdUtil;

@Service
public class ServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo dtlsRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private HttpSession session;

	@Override
	public String login(LoginForm loginForm) {
		UserDtlsEntity entity = dtlsRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());

		if (entity == null) {
			return "Invalid credentials";
		}

		if (entity.getAccStatus().equals("LOCKED")) {
			return "Your Account is locked, UNLOCKED It";
		}

		// create session and store user data in session
		session.setAttribute("userId", entity.getUserId());
		
		return "success";
	}

	@Override
	public boolean signUp(SignUpForm form) {

		UserDtlsEntity user = dtlsRepo.findByEmail(form.getEmail());

		if (user != null) {
			return false;
		}

		// copy data from binding obj to entity obj
		UserDtlsEntity dtlsEntity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, dtlsEntity);

		// generate random password and set to object
		String tempPwd = PwdUtil.generateRandomPassword();
		dtlsEntity.setPwd(tempPwd);

		// set account status as locked
		dtlsEntity.setAccStatus("LOCKED");

		// insert record
		dtlsRepo.save(dtlsEntity);

		// send email to unlocked account
		String to = form.getEmail();
		String subject = "Unlock your account | Ashok IT";

		StringBuffer body = new StringBuffer("");
		body.append("Temparary password: " + tempPwd);

		body.append("</br>");
		body.append("</br>");
		body.append("</br>");

		body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\"> Click here to unlock the account</a>");

		emailUtils.sendEmail(to, subject, body.toString());

		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		UserDtlsEntity dtlsEntity = dtlsRepo.findByEmail(form.getEmail());
		if (dtlsEntity.getPwd().equals(form.getTempPwd())) {
			dtlsEntity.setPwd(form.getNewPwd());
			dtlsEntity.setAccStatus("UNLOCKED");
			dtlsRepo.save(dtlsEntity);

			return true;
		} else {
			return false;
		}

	}

	@Override
	public String forgotPwd(String email) {

		// check record present with same email Id
		UserDtlsEntity entity = dtlsRepo.findByEmail(email);

		// if record not available then send err message
		if (entity == null) {
			return "invalid email,Enter correct email address";
		}

		// if record available send password to email with success message
		String subject = "Recover Password";
		String body = "Your password ::" + entity.getPwd();

		emailUtils.sendEmail(email, subject, body);

		return "Password send to your mail,Check your email addrss";
	}

}
