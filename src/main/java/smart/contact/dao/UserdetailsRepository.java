package smart.contact.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


import smart.contact.entities.Userdetails;

public interface UserdetailsRepository extends CrudRepository<Userdetails,String>{
	Optional<Userdetails> findByEmail(String Email);
}
