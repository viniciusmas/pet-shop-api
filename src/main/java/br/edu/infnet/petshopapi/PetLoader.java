package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.domain.TipoEspecie;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import br.edu.infnet.petshopapi.model.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;

@Order(2)
@Component
@RequiredArgsConstructor
public class PetLoader implements ApplicationRunner {

    private final PetService petService;

    private final ClienteService clienteService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("[PetLoader] Iniciando carregamento do pet do arquivo...");

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("pet.txt")) {

            if (inputStream == null) {
                throw new RuntimeException("Arquivo pet.txt não encontrado em resources");
            }

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                String readLine;

                while ((readLine = bufferedReader.readLine()) != null) {

                    String[] fields = readLine.split(";");

                    String nome = fields[0];
                    TipoEspecie tipoEspecie = TipoEspecie.valueOf(fields[1]);
                    String raca = fields[2];
                    Integer idade = Integer.valueOf(fields[3]);
                    Double peso = Double.valueOf(fields[4]);
                    String cpf = fields[5];

                    ClienteResponseDTO tutor;

                    try {
                        tutor = clienteService.obterPorCpf(cpf);
                        if (tutor == null) {
                            System.err.println("  [ERRO] Tutor com CPF " + cpf + " não encontrado para o pet " + nome);
                            continue;
                        }
                    } catch (ClienteNaoEncontradoException e) {
                        System.err.println("  [ERRO] Tutor com CPF " + cpf + " não encontrado para o pet " + nome);
                        continue;
                    }

                    Pet pet = new Pet();
                    pet.setNome(nome);
                    pet.setTipoEspecie(tipoEspecie);
                    pet.setRaca(raca);
                    pet.setIdade(idade);
                    pet.setPeso(peso);
                    pet.setTutor(new Cliente(tutor));

                    try {
                        petService.incluir(pet);
                        System.out.println("  [OK] Pet " + pet.getNome() + " incluído com sucesso.");
                    } catch (Exception e) {
                        System.err.println("  [ERRO] Problema na inclusão do pet " + pet.getNome() + ": " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("[ERRO] Problema ao ler o arquivo pet.txt: " + e.getMessage());
        }

        System.out.println("[PetLoader] Carregamento concluído.");

        System.out.println("--- Pets Carregados ---");
        petService.obterLista().forEach(System.out::println);
        System.out.println("-----------------------------");
    }

}