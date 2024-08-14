package in.ashokit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
//	
//	@Autowired
//	private HttpSession session;
	
//	@GetMapping("/logout")
//	public String logout() {
//		session.invalidate();
//		return "index";
//	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {

//		System.out.println(loginForm);
		String status = service.login(loginForm);

		if (status.contains("success")) {

			return "redirect:/dashboard";
//			return "dashboard";
		}

		model.addAttribute("errMsg", status);
		return "login";
	}

	@PostMapping("/signup")
	public String handleSignUP(@ModelAttribute("user") SignUpForm form, Model model) {

		boolean signUp = service.signUp(form);

		if (signUp) {
			model.addAttribute("successMsg", "Account is created,Check your email");
		} else {
			model.addAttribute("errMsg", "Errror Occure,This email is already exits");
		}

		return "signup";
	}

	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@GetMapping("/forgot")
	public String fotgotPwdPage() {

		return "forgotPwd";
	}

	@PostMapping("/forgotPwd")
	public String forgot(@RequestParam("email") String email,Model model) {

		System.out.println(email);
		
		String forgotPwd = service.forgotPwd(email);
		
		model.addAttribute("msg", forgotPwd);

		return "forgotPwd";
	}

	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm form, Model model) {

		System.out.println(form);

		if (form.getNewPwd().equals(form.getConfirmPwd())) {
			boolean status = service.unlockAccount(form);

			if (status) {
				model.addAttribute("successMsg", "Your account is unlocked");
			} else {
				model.addAttribute("errMsg", "Given temporary password is incorrect,Checked your email");
			}

		} else {
			model.addAttribute("errMsg", "New Password and Confirm password should be same");
		}

		return "unlock";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm unlockForm = new UnlockForm();
		unlockForm.setEmail(email);

		model.addAttribute("unlock", unlockForm);
		return "unlock";
	}
}
