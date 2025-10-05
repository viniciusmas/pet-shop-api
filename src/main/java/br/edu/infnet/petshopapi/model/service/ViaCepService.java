package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import br.edu.infnet.petshopapi.model.clients.ViaCepFeignClient;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    private final ViaCepFeignClient viaCepFeignClient;

    public ViaCepService(ViaCepFeignClient viaCepFeignClient) {
        this.viaCepFeignClient = viaCepFeignClient;
    }

    public EnderecoRequestDTO getEndereco(String cep) {
        return viaCepFeignClient.findByCep(cep);
    }
}