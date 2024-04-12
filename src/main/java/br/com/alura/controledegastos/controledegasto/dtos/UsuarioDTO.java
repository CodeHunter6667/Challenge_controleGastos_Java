package br.com.alura.controledegastos.controledegasto.dtos;

import br.com.alura.controledegastos.controledegasto.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(Long id,
                         @NotBlank(message = "O Login é obrigatório")
                         @Size(min = 10, max = 25, message = "Login deve ter entre 10 e 25 caracteres")
                         String login,
                         @NotBlank(message = "A Senha é obrigatória")
                         @Size(min = 5, max = 20, message = "Senha deve ter entre 5 e 20 caracteres")
                         String senha,
                         @NotBlank(message = "O Nome é obrigatório")
                         String nome,
                         @NotBlank(message = "O Email é obrigatório")
                         @Email(message = "Email inválido")
                         String email) {
	public UsuarioDTO(Usuario entity) {
		this(entity.getId(), entity.getLogin(), entity.getSenha(), entity.getNome(), entity.getEmail());
	}
}
