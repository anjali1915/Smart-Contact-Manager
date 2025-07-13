package smart.contact.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import smart.contact.service.UserdetailsServices;
import smart.contact.entities.Userdetails;


@Controller
public class MainController {
	@Autowired
	UserdetailsServices userdetailsService;

	@RequestMapping(value="/addcontact", method=RequestMethod.GET)
	public String addContactHandler(Model model) {
		model.addAttribute("formObject",new Userdetails());
		return "addContact";
	}
	
	//handler for form processing
	
	@PostMapping("/process")
	public String formProcesser(@Valid @ModelAttribute("formObject") Userdetails userdetails, BindingResult result,RedirectAttributes redirectAttributes, Model model) {
	    System.out.println(userdetails);
	    model.addAttribute("Userdetails", userdetails); // add this line
	    if(result.hasErrors()) {
	    	System.out.println(result);
	    	return "addContact";
	    }
	    boolean resultant =userdetailsService.adduser(userdetails);
	    if(resultant) {
		    redirectAttributes.addFlashAttribute("success","Data submitted successfully.");

	    } else {
		    redirectAttributes.addFlashAttribute("success","User is already present.");

	    }
	    return "redirect:/addcontact";
	}
	
	@PostMapping("/editprocess")
	public String formProcesseredit(
	        @Valid @ModelAttribute("formObject") Userdetails userdetails,
	        BindingResult result,
	        RedirectAttributes redirectAttributes,
	        Model model) {

	    if (result.hasErrors()) {
	        return "addContact";
	    }

	    Optional<Userdetails> userExist = this.userdetailsService.findByEmail(userdetails.getEmail());

	    if (userExist.isPresent()) {
	        // Preserve the same ID
	        userdetails.setId(userExist.get().getId());
	        userdetailsService.updateData(userdetails);
	        redirectAttributes.addFlashAttribute("success", "User updated successfully.");
	    } else {
	        redirectAttributes.addFlashAttribute("success", "User not found.");
	    }

	    return "redirect:/display";
	}

	
	@GetMapping("/display")
	public String displayData(Model model){
		List<Userdetails> show= this.userdetailsService.displayAll();
		model.addAttribute("userdata",show);
		return "display";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteData(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		this.userdetailsService.deleteuser(id);
		redirectAttributes.addFlashAttribute("success", "User deleted successfully");
		return "redirect:/display";
	}
	
	@GetMapping("/edit/{email}")
	public String editUserForm(@PathVariable("email") String email, Model model, RedirectAttributes redirectAttributes) {
	    Optional<Userdetails> userExist = this.userdetailsService.findByEmail(email);
	    
	    if (userExist.isPresent()) {
	        model.addAttribute("formObject", userExist.get());
	        return "addContact"; // use the same form
	    } else {
	        redirectAttributes.addFlashAttribute("success", "User not found");
	        return "redirect:/display";
	    }
	}

}

