package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import br.edu.infnet.petshopapi.model.clients.ViaCepFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepFeignClient viaCepFeignClient;

    public EnderecoRequestDTO getEnderecoViaCep(String cep) {

        EnderecoRequestDTO enderecoRequestDTO = viaCepFeignClient.findByCep(cep.replace("-", ""));

        if (enderecoRequestDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
        }
        return enderecoRequestDTO;
    }
}