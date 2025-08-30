package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioInvalidoException;
import br.edu.infnet.petshopapi.model.service.FuncionarioService;
import br.edu.infnet.petshopapi.util.Util;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

@Order(3)
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
        String[] fields;

        System.out.println("[FuncionarioLoader] Iniciando carregamento do funcionário do arquivo...");

        while (readLine != null) {

            fields = readLine.split(";");

            Funcionario funcionario = new Funcionario();
            funcionario.setNome(fields[0]);
            funcionario.setCpf(fields[1]);
            funcionario.setRg(fields[2]);
            funcionario.setDataNascimento(Util.dateFormatter(fields[3]));
            funcionario.setSexo(fields[4]);
            funcionario.setEstadoCivil(fields[5]);
            funcionario.setTelefone(fields[6]);
            funcionario.setEmail(fields[7]);
            funcionario.setCargo(fields[14]);
            funcionario.setSalario(new BigDecimal(fields[15]));
            funcionario.setBonus(new BigDecimal(fields[16]));

            Endereco endereco = new Endereco();
            endereco.setLogradouro(fields[8]);
            endereco.setNumero(fields[9]);
            endereco.setBairro(fields[10]);
            endereco.setCidade(fields[11]);
            endereco.setEstado(fields[12]);
            endereco.setCep(fields[13]);

            funcionario.setEndereco(endereco);

            try {
                funcionarioService.incluir(funcionario);
                System.out.println("  [OK] Funcionário " + funcionario.getNome() + " incluído com sucesso.");
            } catch (FuncionarioInvalidoException e) {
                System.err.println("  [ERRO] Problema na inclusão do funcionário " + funcionario.getNome() + ": " + e.getMessage());
            }

            readLine = bufferedReader.readLine();
        }

        System.out.println("[FuncionarioLoader] Carregamento concluído.");

        System.out.println("--- Funcionários Carregados ---");
        funcionarioService.obterLista().forEach(System.out::println);
        System.out.println("-----------------------------");

        bufferedReader.close();

    }
}
