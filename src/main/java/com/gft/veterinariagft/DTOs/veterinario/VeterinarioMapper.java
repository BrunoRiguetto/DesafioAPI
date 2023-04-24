package com.gft.veterinariagft.DTOs.veterinario;

import com.gft.veterinariagft.domain.Perfil;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.util.TipoPerfil;

public class VeterinarioMapper {
    public static Veterinario fromDTO(RegistroVeterinarioDTO dto) {
    	Veterinario veterinario = new Veterinario(null, dto.getEmail(), dto.getSenha(), dto.getNome(), dto.getCrmv());
        veterinario.getPerfils().add(new Perfil(TipoPerfil.ROLE_VETERINARIO));
    	return veterinario;
       
    }

    public static ConsultaVeterinarioDTO fromEntity(Veterinario veterinario) {
        return new ConsultaVeterinarioDTO(veterinario.getId(), veterinario.getNome(), veterinario.getCrmv());
    }
    

}
