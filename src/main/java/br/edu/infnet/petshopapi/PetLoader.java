package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.domain.TipoEspecie;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import br.edu.infnet.petshopapi.model.service.PetService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Order(2)
@Component
public class PetLoader implements ApplicationRunner {

    private final PetService petService;

    private final ClienteService clienteService;

    public PetLoader(PetService petService, ClienteService clienteService) {
        this.petService = petService;
        this.clienteService = clienteService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader fileReader = new FileReader("src/main/resources/pet.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readLine = bufferedReader.readLine();
        String[] fields;

        System.out.println("[PetLoader] Iniciando carregamento do pet do arquivo...");

        while (readLine != null) {

            fields = readLine.split(";");

            String nome = fields[0];
            TipoEspecie tipoEspecie = TipoEspecie.valueOf(fields[1]);
            String raca = fields[2];
            Integer idade = Integer.valueOf(fields[3]);
            Double peso = Double.valueOf(fields[4]);
            String cpf = fields[5];

            Cliente tutor;

            try {
                tutor =  clienteService.obterPorCpf(cpf);
                if(tutor == null) {
                    System.err.println("  [ERRO] Tutor (Cliente) com CPF " + cpf + " não encontrado para o pet " + nome);
                    readLine = bufferedReader.readLine();
                    continue;
                }

            } catch (ClienteNaoEncontradoException e) {
                readLine = bufferedReader.readLine();
                continue;
            }

            Pet pet = new Pet();
            pet.setNome(nome);
            pet.setTipoEspecie(tipoEspecie);
            pet.setRaca(raca);
            pet.setIdade(idade);
            pet.setPeso(peso);
            pet.setTutor(tutor);

            try {
                petService.incluir(pet);
                System.out.println("  [OK] Pet " + pet.getNome() + " incluído com sucesso.");
            } catch (Exception e) {
                System.err.println("  [ERRO] Problema na inclusão do pet " + pet.getNome() + ": " + e.getMessage());
            }

            readLine = bufferedReader.readLine();
        }

        System.out.println("[PetLoader] Carregamento concluído.");

        System.out.println("--- Pets Carregados ---");
        petService.obterLista().forEach(System.out::println);
        System.out.println("-----------------------------");

        bufferedReader.close();
    }
}