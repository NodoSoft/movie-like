package cl.everis.beca.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cl.everis.beca.data.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long>{

	
	@Query("select m from Movie m ORDER BY m.likes DESC")
	List<Movie> movieLikesDesc();
	
	@Query("select m from Movie m ORDER BY m.title ASC")
	List<Movie> movieAlfabetic();
	

}