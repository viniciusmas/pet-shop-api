package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.service.FuncionarioService;
import br.edu.infnet.petshopapi.util.Util;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class FuncionarioLoader implements ApplicationRunner {

    private final FuncionarioService funcionarioService;


    public FuncionarioLoader(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader fileReader = new FileReader("src/main/resources/funcionario.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readLine = bufferedReader.readLine();
        String[] fields = null;

        while (readLine != null) {

            fields = readLine.split(";");

            Funcionario funcionario = new Funcionario();
            funcionario.setNome(fields[0]);
            funcionario.setCpf(Integer.valueOf(fields[1]));
            funcionario.setRg(Integer.valueOf(fields[2]));
            funcionario.setDataNascimento(Util.dateFormatter(fields[3]));
            funcionario.setSexo(fields[4]);
            funcionario.setEstadoCivil(fields[5]);
            funcionario.setTelefone(Integer.valueOf(fields[6]));
            funcionario.setEmail(fields[7]);
            funcionario.setCargo(fields[14]);
            funcionario.setSalario(Double.valueOf(fields[15]));
            funcionario.setBonus(Double.valueOf(fields[16]));

            Endereco endereco = new Endereco();
            endereco.setLogradouro(fields[8]);
            endereco.setNumero(Integer.valueOf(fields[9]));
            endereco.setBairro(fields[10]);
            endereco.setCidade(fields[11]);
            endereco.setEstado(fields[12]);
            endereco.setCep(Integer.valueOf(fields[13]));

            funcionario.setEndereco(endereco);

            funcionarioService.incluir(funcionario);

            System.out.println(funcionario);

            readLine = bufferedReader.readLine();
        }

        System.out.println("Total - " + funcionarioService.obterLista().size());

        bufferedReader.close();

    }
}
