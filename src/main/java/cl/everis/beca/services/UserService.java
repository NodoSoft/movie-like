package cl.everis.beca.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.everis.beca.data.User;
import cl.everis.beca.repositories.IUserRepository;


@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	/**
	 * Autentificacion para login
	 * @param email
	 * @param password
	 * @return
	 */
	public List<User> authenticateUser(String email, String password) {
        
		List<User> user = userRepository.findByEmailAndPassword(email,password);
       
        if(user != null) {
            return user;
        } else {
            return null;
            
        }
    }
	
	/**
	 * Devolver el listado de todos los usuarios del repositorio
	 * @return
	 */
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User addUser(User user) {
		
		return userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	 /**
	  * Encontrar usuario por id
	  * @param id
	  * @return
	  */
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    


}
