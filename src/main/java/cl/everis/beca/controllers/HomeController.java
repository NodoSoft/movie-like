package cl.everis.beca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.everis.beca.data.Like;
import cl.everis.beca.data.Movie;
import cl.everis.beca.data.User;
import cl.everis.beca.services.LikeService;
import cl.everis.beca.services.Login;
import cl.everis.beca.services.MovieService;
import cl.everis.beca.services.UserService;


@RestController
@RequestMapping("/movielike")
public class HomeController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LikeService likeService;
	
	
	/**
	 * Login enviando correo y password.
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<List<User>> login(@RequestBody Login login){
		return new ResponseEntity<List<User>>( userService.authenticateUser(login.getEmail(),login.getPassword()), HttpStatus.OK);
	}

	
	/**
	 * Agregar usuario
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/addUsuario", produces = "application/json")
	public ResponseEntity<User> agregarUsuario(@RequestBody User user){
		return new ResponseEntity<User>( userService.addUser(user), HttpStatus.OK);
	}
		
		
	/**
	 * Agregar Pelicula
	 * @param movie
	 * @return
	 */
	@PostMapping(value = "/addPelicula", produces = "application/json")
	public ResponseEntity<Movie> agregarPelicula(@RequestBody Movie movie){

		return new ResponseEntity<Movie>( movieService.addMovie(movie), HttpStatus.OK);
			
	}
	
	
	/**
	 * Lista todos las peliculas ordenados descendente por likes
	 * @param ordenAsc
	 * @return
	 */
	@GetMapping(value = "/listarPeliculasPorLike", produces = "application/json")
	public ResponseEntity<List<Movie>> listarPorLikes(){
       
    		return new ResponseEntity<List<Movie>>( movieService.movieLikes(), HttpStatus.OK);
		
	}
	
	/**
	 * Lista todos las peliculas ordenados alfabeticamente.
	 * @param ordenAsc
	 * @return
	 */
	@GetMapping(value = "/listarPeliculasAlfabeticamente", produces = "application/json")
	public ResponseEntity<List<Movie>> listarAlfabeticamente(){
    		return new ResponseEntity<List<Movie>>( movieService.allMovies(), HttpStatus.OK);
		
	}
	
	/**
	 * Buscar pelicula por id
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/buscarPelicula", produces = "application/json")
	public ResponseEntity<Movie> verDetallePelicula(@RequestParam Long id){
    		return new ResponseEntity<Movie>( movieService.findMovie(id), HttpStatus.OK);
		
	}
	

	/**
	 * Actualiza la pelicula buscando por id.
	 * 
	 * @param nombre_pelicula
	 * @param fecha
	 * @param descripcion_pelicula
	 * @param puntuacion
	 * @param trailer
	 * @param activa
	 * @param ruta_imagen
	 * @return
	 */
	@PutMapping(value = "/updatePelicula/{id}", produces = "application/json")
	public ResponseEntity<Movie> actualizarPelicula(@PathVariable("id") Long id, @RequestBody Movie movie){
		return new ResponseEntity<Movie>( movieService.updateMovie(id, movie), HttpStatus.OK);
	}
	
	
	
	/**
	 * Eliminar pelicula por id
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/eliminarPeliculaPorId/{id}", produces = "application/json")
	public ResponseEntity<Movie> eliminarPelicula(@PathVariable("id") Long id){

		return new ResponseEntity<Movie>( movieService.deleteMovie(id), HttpStatus.OK);
		
	}
	
	/**
	 * Crear like
	 * @param like
	 * @return
	 */
	@PostMapping(value = "/addLike", produces = "application/json")
	public ResponseEntity<Like> agregarLike(@RequestBody Like like){
		likeService.createLike(like);
		Movie m =movieService.findMovie(like.getMovie_id());
		movieService.sumLike(like.getMovie_id());
		movieService.updateMovie(like.getMovie_id(), m);

		return new ResponseEntity<Like>( likeService.createLike(like), HttpStatus.OK);
		
		
	}
	
	/**
	 * Eliminar like
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/deleteLike", produces = "application/json")
	public ResponseEntity<Like> restLike(@RequestBody Like like){
		
		Movie m =movieService.findMovie(like.getMovie_id());
		movieService.restLike(like.getMovie_id());
		movieService.updateMovie(like.getMovie_id(), m);
		
		return new ResponseEntity<Like>( likeService.deleteLike(like.getMovie_id(),like.getUser_id()), HttpStatus.OK);
	}
	
	
		
}
