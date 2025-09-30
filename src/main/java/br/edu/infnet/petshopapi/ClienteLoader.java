package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import br.edu.infnet.petshopapi.util.Util;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;

@Order(1)
@Component
public class ClienteLoader implements ApplicationRunner {

    private final ClienteService clienteService;

    public ClienteLoader(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader fileReader = new FileReader("src/main/resources/cliente.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readLine = bufferedReader.readLine();
        String[] fields;

        System.out.println("[ClienteLoader] Iniciando carregamento do cliente do arquivo...");

        while (readLine != null) {

            fields = readLine.split(";");

            Cliente cliente = new Cliente();
            cliente.setNome(fields[0]);
            cliente.setCpf(fields[1]);
            cliente.setRg(fields[2]);
            cliente.setDataNascimento(Util.dateFormatter(fields[3]));
            cliente.setSexo(fields[4]);
            cliente.setEstadoCivil(fields[5]);
            cliente.setTelefone(fields[6]);
            cliente.setEmail(fields[7]);
            cliente.setClienteDeste(Util.dateFormatter(fields[8]));
            cliente.setStatus(Boolean.valueOf(fields[9]));
            cliente.setCepConsulta(fields[10]);

            try {
                clienteService.incluir(cliente);
                System.out.println("  [OK] Cliente " + cliente.getNome() + " incluído com sucesso.");
            } catch (Exception e) {
                System.err.println("  [ERRO] Problema na inclusão do cliente " + cliente.getNome() + ": " + e.getMessage());
            }

            readLine = bufferedReader.readLine();
        }

        System.out.println("[ClienteLoader] Carregamento concluído.");

        System.out.println("--- Clientes Carregados ---");
        clienteService.obterLista().forEach(System.out::println);
        System.out.println("-----------------------------");

        bufferedReader.close();
    }
}