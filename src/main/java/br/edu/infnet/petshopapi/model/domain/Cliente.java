package br.edu.infnet.petshopapi.model.domain;

import br.edu.infnet.petshopapi.model.dto.ClienteRequestDTO;
import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Cliente extends Pessoa {

    private LocalDateTime clienteDeste;

    private Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pet> pets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Agendamento> agendamentos;

    public Cliente() {}

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        this.setNome(clienteRequestDTO.getNome());
        this.setCpf(clienteRequestDTO.getCpf());
        this.setRg(clienteRequestDTO.getRg());
        this.setDataNascimento(clienteRequestDTO.getDataNascimento());
        this.setSexo(clienteRequestDTO.getSexo());
        this.setEstadoCivil(clienteRequestDTO.getEstadoCivil());
        this.setTelefone(clienteRequestDTO.getTelefone());
        this.setEmail(clienteRequestDTO.getEmail());
        this.setClienteDeste(clienteRequestDTO.getClienteDeste());
        this.setStatus(clienteRequestDTO.getStatus());
        this.setCepConsulta(clienteRequestDTO.getCepConsulta());
    }

    public Cliente(ClienteResponseDTO clienteResponseDTO) {
        this.setId(clienteResponseDTO.getId());
        this.setNome(clienteResponseDTO.getNome());
        this.setCpf(clienteResponseDTO.getCpf());
        this.setRg(clienteResponseDTO.getRg());
        this.setDataNascimento(clienteResponseDTO.getDataNascimento());
        this.setSexo(clienteResponseDTO.getSexo());
        this.setEstadoCivil(clienteResponseDTO.getEstadoCivil());
        this.setTelefone(clienteResponseDTO.getTelefone());
        this.setEmail(clienteResponseDTO.getEmail());
        this.setEndereco(clienteResponseDTO.getEndereco());
        this.setClienteDeste(clienteResponseDTO.getClienteDeste());
        this.setStatus(clienteResponseDTO.getStatus());
        this.setCepConsulta(clienteResponseDTO.getCepConsulta());
        this.setPets(clienteResponseDTO.getPets());
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Cliente { %s Cliente Deste = %s, Status = %s }", super.toString(), clienteDeste.format(dateTimeFormatter), status ? "Ativo" : "Inativo");
    }

    @Override
    public String obterTipo() {
        return "Cliente";
    }
}
