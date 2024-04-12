package br.com.alura.controledegastos.controledegasto.repositories;

import br.com.alura.controledegastos.controledegasto.models.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    UserDetails findByLogin(String login);
    
    @Query("SELECT u FROM Usuario u WHERE u.login = ?1")
    Usuario procuraUserPorLogin(String login);
}
