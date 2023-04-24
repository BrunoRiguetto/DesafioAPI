package com.gft.veterinariagft.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gft.veterinariagft.domain.Atendimento;
import com.gft.veterinariagft.domain.Cachorro;
import com.gft.veterinariagft.domain.Cliente;
import com.gft.veterinariagft.domain.DadosAnimal;
import com.gft.veterinariagft.domain.Usuario;
import com.gft.veterinariagft.domain.Veterinario;
import com.gft.veterinariagft.domain.dogapi.Breed;
import com.gft.veterinariagft.repositories.AtendimentoRepository;
import com.gft.veterinariagft.repositories.CachorroRepository;
import com.gft.veterinariagft.repositories.ClienteRepository;
import com.gft.veterinariagft.repositories.UsuarioRepository;
import com.gft.veterinariagft.repositories.VeterinarioRepository;
import com.gft.veterinariagft.services.DogApiService;
import com.gft.veterinariagft.util.Porte;
import com.gft.veterinariagft.util.TipoPerfil;

@Configuration
public class Banco implements CommandLineRunner {

        private final UsuarioRepository usuarioRepository;
        private final CachorroRepository cachorroRepository;
        private final ClienteRepository clienteRepository;
        private final VeterinarioRepository veterinarioRepository;
        private final AtendimentoRepository atendimentoRepository;
        private final DogApiService dogService;

        public Banco(CachorroRepository cachorroRepository, ClienteRepository clienteRepository,
                        VeterinarioRepository veterinarioRepository, AtendimentoRepository atendimentoRepository,
                        UsuarioRepository usuarioRepository, DogApiService dogService) {
                this.cachorroRepository = cachorroRepository;
                this.clienteRepository = clienteRepository;
                this.veterinarioRepository = veterinarioRepository;
                this.atendimentoRepository = atendimentoRepository;
                this.usuarioRepository = usuarioRepository;
                this.dogService = dogService;
        }

        @Override
        public void run(String... args) throws Exception {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                atendimentoRepository.deleteAll();
                cachorroRepository.deleteAll();
                veterinarioRepository.deleteAll();
                clienteRepository.deleteAll();

                usuarioADMIN();

                Cliente cl1 = new Cliente("clecio@gft.com", "123456", "Clecio", "333.444.555-66", "12345-67890");
                cl1.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                Cliente cl2 = new Cliente("michel@gft.com", "123456", "Michel", "777.888.999-00", "12345-67890");
                cl2.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                Cliente cl3 = new Cliente("ubiratan@gft.com", "123456", "Ubiratan", "111.222.333-44", "12345-67890");
                cl3.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                Cliente cl4 = new Cliente("joao@gft.com", "123456", "Joao", "555.666.777.88", "12345-67890");
                cl1.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                Cliente cl5 = new Cliente("jose@gft.com", "123456", "Jose", "666.777.888-99", "12345-67890");
                cl1.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                clienteRepository.saveAll(Arrays.asList(cl1, cl2, cl3, cl4, cl5));

                Cachorro ca1 = new Cachorro("Pitoco", null, 40.2, Porte.GRANDE, cl3);
                Cachorro ca2 = new Cachorro("Junin", null, 20.2, Porte.MEDIO, cl2);
                Cachorro ca3 = new Cachorro("Catita", null, 10.0, Porte.MEDIO, cl1);
                Cachorro ca4 = new Cachorro("Jeguelson", null, 4.7, Porte.PEQUENO, cl5);
                Cachorro ca5 = new Cachorro("Tubi", null, 7.0, Porte.PEQUENO, cl4);
                Cachorro ca6 = new Cachorro("Spok", null, 7.0, Porte.PEQUENO, cl2);
                Cachorro ca7 = new Cachorro("Thor", null, 7.0, Porte.GRANDE, cl2);

                List<Cachorro> cachorros = new ArrayList<>(Arrays.asList(ca1, ca2, ca3, ca4, ca5, ca6, ca7));

                Random r = new Random();
                int idRacaMinimo = 1;
                int idRacaMaximo = 264;

                for(Cachorro c : cachorros) {
                        int randomRaca = r.nextInt(idRacaMaximo-idRacaMinimo) + idRacaMinimo;
                        Breed b = dogService.buscarRacaPorId(randomRaca);

                        while(b == null) {
                                randomRaca = r.nextInt(idRacaMaximo-idRacaMinimo) + idRacaMinimo;
                                b = dogService.buscarRacaPorId(randomRaca);
                        }

                        c.buildRacaDescricao(b);
                        c.setRaca(b.getName());   
                }

                cachorroRepository.saveAll(cachorros);

                Veterinario v1 = new Veterinario("fulano@gft.com", "123456", "Fulano", "123456789");
                v1.adicionaPerfil(TipoPerfil.ROLE_VETERINARIO);

                Veterinario v2 = new Veterinario("ciclano@gft.com", "123456", "Ciclano", "234567891");
                v2.adicionaPerfil(TipoPerfil.ROLE_VETERINARIO);
                v2.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                Veterinario v3 = new Veterinario("beltrano@gft.com", "123456", "Beltrano", "345678901");
                v3.adicionaPerfil(TipoPerfil.ROLE_VETERINARIO);
                v3.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                Veterinario v4 = new Veterinario("zeca@gft.com", "123456", "Zeca", "4567890123");
                v4.adicionaPerfil(TipoPerfil.ROLE_VETERINARIO);
                v4.adicionaPerfil(TipoPerfil.ROLE_CLIENTE);

                Veterinario v5 = new Veterinario("tiao@gft.com", "123456", "Tiao", "5678901234");
                v5.adicionaPerfil(TipoPerfil.ROLE_VETERINARIO);

                veterinarioRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5));

                Atendimento a1 = new Atendimento(sdf.parse("22/07/2022"), "13h",
                                new DadosAnimal(30.0, "Dor e febre", 12.8), "Virose", "Cliente não pagou", ca1, v1,
                                cl3);
                Atendimento a2 = new Atendimento(sdf.parse("13/01/2018"), "9h",
                                new DadosAnimal(31.5, "Hemorragia interna", 11.0), "Falha dos orgãos", "", ca4, v4,
                                cl5);
                Atendimento a3 = new Atendimento(sdf.parse("01/12/2019"), "14h",
                                new DadosAnimal(30.2, "Febre, nauseas e vomito", 10.0), "Gripe suina", "Passar vacina",
                                ca2, v4, cl2);
                Atendimento a4 = new Atendimento(sdf.parse("17/11/2021"), "10h",
                                new DadosAnimal(28.3, "Dor na pata", 11.2), "Febre do macaco",
                                "Pedir pomada pro fornecedor", ca3, v2,
                                cl1);
                Atendimento a5 = new Atendimento(sdf.parse("21/05/2022"), "8h", new DadosAnimal(37.1, "Espirros", 12.1),
                                "Excesso de fast food", "paciente alergico a dipirona", ca5, v2, cl4);

                atendimentoRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));
        }

        private void usuarioADMIN() {
                usuarioRepository.deleteAll();
                Usuario usuario = new Usuario(null, "admin@gft.com","gft@1234" );
                usuario.adicionaPerfil(TipoPerfil.ROLE_ADMIN);
                usuarioRepository.save(usuario);
        }

}
