package com.christiansanchez.babynames.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.christiansanchez.babynames.models.LoginUser;
import com.christiansanchez.babynames.models.Name;
import com.christiansanchez.babynames.models.User;
import com.christiansanchez.babynames.services.NameService;
import com.christiansanchez.babynames.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private NameService nameService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", userService.oneUser(userId));
			List<Name> names = nameService.allNames();
			model.addAttribute("names", names);
		
			return "dashboard.jsp";
		}
		
	}
	
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
       
        User user = userService.register(newUser, result);
        
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }else {
        	session.setAttribute("userId", user.getId());
        
        	return "redirect:/dashboard";
        }
	}
	
	
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        User user = userService.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }else {
        	session.setAttribute("userId", user.getId());
        	session.setAttribute("userName", user.getUserName());
        
        	return "redirect:/dashboard";
        }
	}
	
	
	@GetMapping("/names/new")
	public String addName(@ModelAttribute("name") Name name, HttpSession session) {
		 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}else {
		 
			return "new.jsp";
		}
	}
	
	@PostMapping("/names/new")
	public String addName(@Valid @ModelAttribute("name") Name name, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "new.jsp";
		}else {
			nameService.addName(new Name(name.getName(), name.getGender(), name.getOrigin(), 
					name.getMeaning(), userService.oneUser(userId)));
			return "redirect:/dashboard";
		}
	}
		
		
	
	@GetMapping("/names/{id}")
	public String showName(@PathVariable("id") Long id, HttpSession session, Model model) {
	 
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", userService.oneUser(userId));
			Name name = nameService.findName(id);
			model.addAttribute("name", name);
	     
	    	return "show.jsp";
		}
	}
	
	@GetMapping("/names/edit/{id}")
	public String editName(@PathVariable("id") Long id, HttpSession session, Model model) {	
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}else {
			Name name = nameService.findName(id);
			model.addAttribute("name", name);
			return "edit.jsp";
		}
	}
	
	@PutMapping("/names/edit/{id}")
	public String updateName(@PathVariable("id") Long id, @Valid @ModelAttribute("name") Name name, 
			BindingResult result, HttpSession session) {	
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		 
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			nameService.updateName(name);
			
			return "redirect:/dashboard";
		}
	}
	
	@DeleteMapping("/names/delete/{id}")
	public String destroy(@PathVariable("id")Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}else {
			nameService.deleteName(nameService.findName(id));
			return "redirect:/dashboard";
		}
	}
	
	
	
	//logs out user 
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
	
	
}
