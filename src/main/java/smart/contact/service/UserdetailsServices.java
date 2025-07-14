package smart.contact.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.contact.entities.*;
import smart.contact.dao.UserdetailsRepository;

@Service
public class UserdetailsServices {

    @Autowired
    private UserdetailsRepository userdetailsRepository;

    // Display all users
  //to display complete data
  	public List<Userdetails> displayAll() {
  		List<Userdetails> list =(List<Userdetails>)this.userdetailsRepository.findAll();
  		return list;
  	}
    // Add user
    public boolean addUser(Userdetails userData) {
        Optional<Userdetails> existingUser = userdetailsRepository.findByEmail(userData.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }
        userdetailsRepository.save(userData);
        return true;
    }

    // Update user data
    public void updateData(Userdetails updatedUserData) {
        Optional<Userdetails> existingUser = userdetailsRepository.findByEmail(updatedUserData.getEmail());
        if (existingUser.isPresent()) {
            Userdetails updatedUser = existingUser.get();
            updatedUser.setFname(updatedUserData.getFname());
            updatedUser.setLname(updatedUserData.getLname());
            updatedUser.setAddress(updatedUserData.getAddress());
            updatedUser.setMobile(updatedUserData.getMobile());
            userdetailsRepository.save(updatedUser);
        }
    }

    // Find user by email
    public Optional<Userdetails> findByEmail(String email) {
        return userdetailsRepository.findByEmail(email);
    }

    // Delete user
    public void deleteUser(String email) {
        if (userdetailsRepository.existsById(email)) {
            userdetailsRepository.deleteById(email);
        }
    }
}
