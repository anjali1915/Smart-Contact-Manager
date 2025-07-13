package smart.contact.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smart.contact.entities.*;
import smart.contact.dao.UserdetailsRepository;

@Component
public class UserdetailsServices {
	@Autowired
	UserdetailsRepository userdetailsRepository;
	
	//to display complete data
	public List<Userdetails> displayAll() {
		List<Userdetails> list =(List<Userdetails>)this.userdetailsRepository.findAll();
		return list;
	}
	
	//to create
	public boolean adduser(Userdetails userData) {
		Optional<Userdetails> existingUser = userdetailsRepository.findByEmail(userData.getEmail());
		if(existingUser.isPresent()) {
			return false;
		}
		this.userdetailsRepository.save(userData);
		return true;
	}
	
	
	//update 
	 public void updateData(Userdetails updatedUserData) {
		 
		  Optional<Userdetails> existingUser = this.userdetailsRepository.findByEmail(updatedUserData.getEmail());
		  if(existingUser.isPresent()) {
			  Userdetails updatedUser = existingUser.get();
			  updatedUser.setId(updatedUserData.getId()) ;
			  updatedUser.setFname(updatedUserData.getFname());
              updatedUser.setLname(updatedUserData.getLname());
              updatedUser.setAddress(updatedUserData.getAddress());
              updatedUser.setMobile(updatedUserData.getMobile());
    		  userdetailsRepository.save(updatedUserData);
		  }		 
	 }
	 
	//check on the basic of email
	 public Optional<Userdetails> findByEmail(String email) {
		 return this.userdetailsRepository.findByEmail(email);
	 }
	 
	//delete user
	public void deleteuser(int id) {
		this.userdetailsRepository.deleteById(id);
	}
}



