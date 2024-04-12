package br.com.alura.controledegastos.controledegasto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.controledegastos.controledegasto.models.Usuario;
import br.com.alura.controledegastos.controledegasto.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}

}
