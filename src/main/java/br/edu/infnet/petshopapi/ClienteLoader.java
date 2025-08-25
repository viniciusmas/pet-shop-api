package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import br.edu.infnet.petshopapi.util.Util;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;

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
        String[] fields = null;

        while (readLine != null) {

            fields = readLine.split(";");

            Cliente cliente = new Cliente();
            cliente.setNome(fields[0]);
            cliente.setCpf(Integer.valueOf(fields[1]));
            cliente.setRg(Integer.valueOf(fields[2]));
            cliente.setDataNascimento(Util.dateFormatter(fields[3]));
            cliente.setSexo(fields[4]);
            cliente.setEstadoCivil(fields[5]);
            cliente.setTelefone(Integer.valueOf(fields[6]));
            cliente.setEmail(fields[7]);
            cliente.setClienteDeste(Util.dateFormatter(fields[8]));
            cliente.setAtivo(Boolean.valueOf(fields[9]));

            Endereco endereco = new Endereco();
            endereco.setLogradouro(fields[10]);
            endereco.setNumero(Integer.valueOf(fields[11]));
            endereco.setBairro(fields[12]);
            endereco.setCidade(fields[13]);
            endereco.setEstado(fields[14]);
            endereco.setCep(Integer.valueOf(fields[15]));

            cliente.setEndereco(endereco);

            clienteService.incluir(cliente);

            readLine = bufferedReader.readLine();
        }

        clienteService.obterLista().forEach(System.out::println);

        bufferedReader.close();
    }
}