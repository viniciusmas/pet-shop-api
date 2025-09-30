package br.edu.infnet.petshopapi.model.clients;

import br.edu.infnet.petshopapi.model.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${api.viacep.url}")
public interface ViaCepFeignClient {

    @GetMapping("/{cep}/json/")
    EnderecoDTO findByCep(@PathVariable String cep);
}