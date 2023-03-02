package com.christiansanchez.bookclub.controllers;

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

import com.christiansanchez.bookclub.models.Book;
import com.christiansanchez.bookclub.models.LoginUser;
import com.christiansanchez.bookclub.models.User;
import com.christiansanchez.bookclub.services.BookService;
import com.christiansanchez.bookclub.services.UserService;


@Controller
public class HomeController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	//loads login/registration pages. gets User attributes from models
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	
	//once logged in/registered, loads dashboard page that shows all books 
	@GetMapping("/dashboard")
	public String home(HttpSession session, Model model) {
		
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", userService.oneUser(userId));
			List<Book> books = bookService.allBooks();
			model.addAttribute("books", books);
		
			return "dashboard.jsp";
		}
	}
	
	
	//creates user in database and stores user Id in session
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
	
	
	//logins user and stores user Id in session
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
	
	
	//when link is clicked, returns new page to create new book
	@GetMapping("/newBook")
	public String addBook(@ModelAttribute("book") Book book, HttpSession session) {
		 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}else {
		 
			return "new_book.jsp";
		}
	}
	
	
	//when form is submitted, adds book to database and returns to dash with all books created 
	@PostMapping("/newBook")
	public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
		
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			
			return "redirect:/logout";
		}
		
	    if(result.hasErrors()) {
	    	
	    	return "new_book.jsp";
	    }else {
	    	
	    	bookService.addBook(new Book(book.getTitle(), book.getAuthor(), book.getThoughts(), 
	    			userService.oneUser(userId)));
	    	
	    	return "redirect:/dashboard";
	    }
	}
	
	
	//when book link is clicked, opens new page to show book and details
	@GetMapping("/books/{id}")
	public String showBook(@PathVariable("id") Long id, HttpSession session, Model model) {
	 
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", userService.oneUser(userId));
			Book book = bookService.findBook(id);
			model.addAttribute("book", book);
	     
	    	return "show_book.jsp";
		}
	}
	
	
	//when edit link is clicked, goes to new page that lets book creator edit entry
	@GetMapping("/books/edit/{id}")
	public String edit(@PathVariable("id") Long id, HttpSession session, Model model) {	
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}else {
			Book book = bookService.findBook(id);
			model.addAttribute("book", book);
			return "edit_book.jsp";
		}
	}
	
	
	//when update page is submitted, saves changes and returns to the dashboard with updates
	@PutMapping("/books/edit/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, 
			BindingResult result, HttpSession session) {	
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		 
		if(result.hasErrors()) {
			return "edit_book.jsp";
		}else {
			bookService.updateBook(book);
			
			return "redirect:/dashboard";
		}
	}
	
	
	//deletes book if user that created it is logged in
	@DeleteMapping("/books/delete/{id}")
	public String destroy(@PathVariable("id")Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}else {
			bookService.deleteBook(bookService.findBook(id));
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
