package com.example.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.NewUserModel;
import com.example.model.UserModel;
import com.example.service.NewUserService;
import com.example.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Resource
	UserService userService;

	@Resource
	NewUserService newUserService;

	//ホーム画面系を表示
	@GetMapping
	public String home() {
		return "home";
	}

	@PostMapping("/home")
	public String home1() {

		return "home";
	}

	//データインポートの画面系
	@GetMapping("/DataInput")
	public String datainput() {
		return "DataInput";
	}

	@PostMapping("/DataInput")
	public String datainputIndex(UserModel userModel, Model model) {

		return "DataInput";
	}

	//管理者のログイン画面系
	@GetMapping("/AdminLogin")
	public String Admin() {
		return "AdminLogin";
	}

	@PostMapping("/GoToAdminLogin")
	public String GoToAdminLogin(UserModel userModel, Model model) {

		return "AdminLogin";
	}

	@PostMapping("/GTDataList")
	public String login1(UserModel userModel, Model model) {

		if (userModel.getName().equals("") || userModel.getPass().equals("")) {
			model.addAttribute("msg", "全部入力してください。");
			return "AdminLogin";

		} else if (userModel.getName().equals("admin") && userModel.getPass().equals("admin")) {
			List<UserModel> a = userService.takeData(userModel);
			model.addAttribute("a", a);
			return "DataList";
		} else if (!userModel.getName().equals("admin") || !userModel.getPass().equals("admin")) {
			model.addAttribute("msg", "アカウント存在しないです。");
			return "AdminLogin";
		}
		return "";
	}

	//データを表示画面系
	@GetMapping("/DataList")
	public String DataList(Model model, UserModel userModel) {
		return "DataList";
	}

	@PostMapping("/DataList")
	public String Datalisthensu(UserModel userModel, Model model) {

		return "DataList";
	}

	//insertの条件
	@PostMapping("/save")
	public String login(UserModel userModel, Model model) {

		if (checkNameExits(userModel, model) == true) {
			LocalDate endDate = LocalDate.parse(userModel.getDay().toString());
			LocalDate today = LocalDate.now();
			long totalDays = ChronoUnit.DAYS.between(today, endDate);
			long years = totalDays / 365;
			long remainingDays = totalDays % 365;
			int months = (int) (remainingDays / 30.44);
			int days = (int) (remainingDays % 30.44);
			if (totalDays < 0) {
				totalDays = 0;
			}

			if (totalDays > 30.44) {

				String remaining = String.format(" %d 月, %d 日", months, days);
				userModel.setRemaining(remaining);
			}
			if ((totalDays > 365)) {
				String remaining = String.format(" %d 年 %d 月, %d 日", years, months, days);
				userModel.setRemaining(remaining);
			}
			if (totalDays < 30.44) {
				String RemDay = String.format(" %d 日", days);
				userModel.setRemaining(RemDay);
			}

			model.addAttribute("msg", "保存できました！");
			userService.insert(userModel);
			return "home";
		} else {
			return "DataInput";
		}
	}

	//chia ngày tháng năm 

	private boolean checkNameExits(UserModel userModel, Model model) {
		boolean isAllInput = true;
		boolean isAccountExits = true;
		UserModel checkAcc = userService.checkAccount(userModel);
		if (userModel.getName() == "" || userModel.getDay() == "") {
			model.addAttribute("msg", "全部入力してください。");
			isAllInput = false;
		}
		if (checkAcc != null) {
			model.addAttribute("msg", "あなたの名前は他の人の名前と一致しています。名前の後に 1、2、または 3 を追加してください。");
			isAccountExits = false;
		}
		if (isAllInput == true && isAccountExits == true) {
			model.addAttribute("msg", "保存できました。");
			return true;
		} else
			return false;
	}

	//編集
	@GetMapping("/Update/{id}")
	public String getupdateid(@PathVariable("id") int id, Model model, NewUserModel newUserModel) {

		List<NewUserModel> b = newUserService.selectupdate(newUserModel);
		model.addAttribute("b", b);
		return "Update";
	}

	@PostMapping("/Update/{id}")
	public String update(NewUserModel newUserModel, Model model, BindingResult result, UserModel userModel) {
		newUserService.update(newUserModel);
		//		System.out.println(newUserService.update(newUserModel));
		List<UserModel> a = userService.takeData(userModel);
		model.addAttribute("a", a);
		return "DataList";
	}

	//delete
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, UserModel userModel, Model model) {
		userService.deleteUser(userModel);
		List<UserModel> a = userService.takeData(userModel);
		model.addAttribute("a", a);
		return "DataList";
	}

}