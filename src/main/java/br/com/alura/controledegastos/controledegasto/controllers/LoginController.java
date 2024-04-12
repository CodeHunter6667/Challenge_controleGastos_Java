package br.com.alura.controledegastos.controledegasto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.controledegastos.controledegasto.dtos.LoginDTO;
import br.com.alura.controledegastos.controledegasto.dtos.TokenDTO;
import br.com.alura.controledegastos.controledegasto.dtos.UsuarioDTO;
import br.com.alura.controledegastos.controledegasto.models.Usuario;
import br.com.alura.controledegastos.controledegasto.security.TokenService;
import br.com.alura.controledegastos.controledegasto.services.UsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity login(@RequestBody LoginDTO dto) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
		var authentication = manager.authenticate(authenticationToken);
		
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.ok(new TokenDTO(tokenJWT));
	}
}
