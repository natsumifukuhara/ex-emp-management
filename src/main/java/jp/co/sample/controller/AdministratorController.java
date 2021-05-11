package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")


public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private HttpSession session;

	//
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm()
	{
		InsertAdministratorForm form = new InsertAdministratorForm();
		return form;
		
	}
	
	//
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	//
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm insertAdministratorForm) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(insertAdministratorForm, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	//
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm form = new LoginForm();
		return form;
	}
	
	//
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	//
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		administratorService.login(form.getMailAddress(),form.getPassword());
		Administrator administrator = new Administrator();
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());

		if(administrator == null) {
			System.out.println("不正です");
			
		}else {
			//ここ直す
		session.setAttribute("administratorName", administrator);
		
		}
		return "foward:/employee/showList";
		}
	}

	

