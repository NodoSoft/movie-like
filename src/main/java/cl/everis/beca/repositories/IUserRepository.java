package cl.everis.beca.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.everis.beca.data.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	@Transactional
	List<User> findByEmailAndPassword(String email, String password);
	
}