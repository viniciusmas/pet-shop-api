package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import br.edu.infnet.petshopapi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;

@Order(1)
@Component
@RequiredArgsConstructor
public class ClienteLoader implements ApplicationRunner {

    private final ClienteService clienteService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("[ClienteLoader] Iniciando carregamento do cliente do arquivo...");

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cliente.txt")) {

            if (inputStream == null) {
                throw new RuntimeException("Arquivo funcionario.txt não encontrado em resources");
            }

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String readLine;

                while ((readLine = bufferedReader.readLine()) != null) {
                    String[] fields = readLine.split(";");

                    if (fields.length < 11) {
                        System.err.println("  [ERRO] Linha inválida: " + readLine);
                        continue;
                    }

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
                    cliente.setStatus(Boolean.parseBoolean(fields[9]));
                    cliente.setCepConsulta(fields[10]);

                    try {
                        clienteService.incluir(cliente);
                        System.out.println("  [OK] Cliente " + cliente.getNome() + " incluído com sucesso.");
                    } catch (Exception e) {
                        System.err.println("  [ERRO] Problema na inclusão do cliente " + cliente.getNome() + ": " + e.getMessage());
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("[ERRO] Problema ao ler o arquivo cliente.txt: " + e.getMessage());
        }

        System.out.println("[ClienteLoader] Carregamento concluído.");
        System.out.println("--- Clientes Carregados ---");
        clienteService.obterLista().forEach(System.out::println);
        System.out.println("-----------------------------");
    }

}