package br.com.alura.controledegastos.controledegasto.services;

import br.com.alura.controledegastos.controledegasto.dtos.LoginDTO;
import br.com.alura.controledegastos.controledegasto.dtos.UsuarioDTO;
import br.com.alura.controledegastos.controledegasto.models.Usuario;
import br.com.alura.controledegastos.controledegasto.repositories.UsuarioRepository;
import br.com.alura.controledegastos.controledegasto.services.exceptions.UsuarioNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto){
        var usuario = new Usuario();
        CopyDtoToEntity(dto, usuario);
        var senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        repository.save(usuario);
        return new UsuarioDTO(usuario);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if(!repository.existsById(id)) {
    		throw new UsuarioNotFoundException("Usuario não encontrado");
    	}
    	repository.deleteById(id);
    }
    
    private static void CopyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setId(dto.id());
        entity.setLogin(dto.login());
        entity.setSenha(dto.senha());
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
    }
}
