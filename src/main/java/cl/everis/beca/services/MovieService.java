package cl.everis.beca.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.everis.beca.data.Movie;
import cl.everis.beca.repositories.IMovieRepository;



@Service
public class MovieService {

	@Autowired
	private IMovieRepository movieRepository;
	
	/**
	 * Agregar pelicula
	 * @param movie
	 * @return
	 */
	public Movie addMovie(Movie movie) {
		
		return movieRepository.save(movie);
	}
	
	/**
	 * Devolver todas las peliculas
	 * @return
	 */
	public List<Movie> allMovies(){
		List<Movie> temp=movieRepository.movieAlfabetic();

		return temp;
	}
	
	/**
	 * Devolver listado ordenado por likes
	 * @param orden
	 * @return
	 */
	public List<Movie> movieLikes(){
		List<Movie> temp= movieRepository.movieLikesDesc();
		return temp;
	}

    
	/**
	 * Encontrar pelicula con id
	 * @param id
	 * @return
	 */
    public Movie findMovie(Long id) {
    	Optional<Movie> m = movieRepository.findById(id);
    	
    	if(m.isPresent()) {
            return m.get();
    	} else {
    	    return null;
    	}
  
    }
   
   
   /**
    * Actualizar datos de la pelicula
    * @param user
    * @return
    */
	public Movie updateMovie(Long id, Movie movie){
		// TODO Auto-generated method stub
				//return userDaoImpl.updateUser(id, user);
				Optional<Movie> temp = movieRepository.findById(id);
				if (temp.isPresent()) {
					temp.get().setTitle(movie.getTitle());
					temp.get().setYear(movie.getYear());
					temp.get().setDescription(movie.getDescription());
					temp.get().setTrailer(movie.getTrailer());
					temp.get().setActiva(movie.getActiva());
					temp.get().setImagen(movie.getImagen());
					movieRepository.save(temp.get());
					return temp.get();
				} else {
					return null;
				} 
   }
	
	/**
	 * Eliminar pelicula
	 * @param id
	 */
    public Movie deleteMovie(Long id) {
    	Optional<Movie> temp = movieRepository.findById(id);
    	movieRepository.deleteById(id);
    	return temp.get();
    			
    }
    
    /**
     * Sumar like
     * @param id
     */
    public void sumLike(Long id) {
    	int templikes;
    	Optional<Movie> m = movieRepository.findById(id);
    	if(m.isPresent()) {
    		templikes=m.get().getLikes();
    		templikes=templikes+1;
    		m.get().setLikes(templikes);
 
    		System.out.println(m.get().getLikes());
    	}
    	
    	
    }
    
    /**
     * Restar like
     * @param id
     */
    public void restLike(Long id) {
    	int templikes;
    	Optional<Movie> m = movieRepository.findById(id);
    	if(m.isPresent()) {
    		templikes=m.get().getLikes();
    		templikes=templikes-1;
    		m.get().setLikes(templikes);
 

    	}
    	
    	
    }
    
    
 }
