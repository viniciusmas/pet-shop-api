package br.edu.infnet.petshopapi.model.clients;

import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${api.viacep.url}")
public interface ViaCepFeignClient {

    @GetMapping("/{cep}/json/")
    EnderecoRequestDTO findByCep(@PathVariable String cep);
}