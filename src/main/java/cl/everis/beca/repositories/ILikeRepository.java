package cl.everis.beca.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cl.everis.beca.data.Like;

public interface ILikeRepository extends JpaRepository<Like, Long>{

	/**
	 * Muestra todas los likes registrados
	 */
	List<Like> findAll();
	
	/**
	 * Elimina like por id
	 * @param id
	 * @return
	 */
	List<Like> deleteById(int id);
	
	/**
	 * Borrar por user_id y movie_id
	 * @param movieid
	 * @param userid
	 * @return
	 */
	@Query("select l from Like l  where l.user_id= ?1 and l.movie_id= ?2")
	List<Like> deleteByMovie_IdAndUser_Id(Long movieid, Long userid);
	
	@Query("select l from Like l  where l.user_id= ?1 and l.movie_id= ?2")
	Like findByMovie_IdAndUser_Id(Long idmovie, Long iduser);
}
