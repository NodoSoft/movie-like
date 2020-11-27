package cl.everis.beca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.everis.beca.data.Like;

import cl.everis.beca.repositories.ILikeRepository;


@Service
public class LikeService {
	

	
	@Autowired
	private ILikeRepository likeRepository;
	
	/**
	 * Devolver el listado de todos los usuarios del repositorio
	 * @return
	 */
	public List<Like> findAll(){
		return likeRepository.findAll();
	}
	
	public Like createLike(Like like) {
		return likeRepository.save(like);
	}
	
	public Like deleteLike(Long idmovie, Long iduser) {
		
		likeRepository.deleteByMovie_IdAndUser_Id(idmovie, iduser);
		return likeRepository.findByMovie_IdAndUser_Id(idmovie, iduser);
	}

}
