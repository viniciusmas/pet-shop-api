package br.edu.infnet.petshopapi;

import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioInvalidoException;
import br.edu.infnet.petshopapi.model.service.FuncionarioService;
import br.edu.infnet.petshopapi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;

@Order(3)
@Component
@RequiredArgsConstructor
public class FuncionarioLoader implements ApplicationRunner {

    private final FuncionarioService funcionarioService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("[FuncionarioLoader] Iniciando carregamento do funcionário do arquivo...");

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("funcionario.txt")) {

            if (inputStream == null) {
                throw new RuntimeException("Arquivo funcionario.txt não encontrado em resources");
            }

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String readLine;

                while ((readLine = bufferedReader.readLine()) != null) {
                    String[] fields = readLine.split(";");

                    Funcionario funcionario = new Funcionario();
                    funcionario.setNome(fields[0]);
                    funcionario.setCpf(fields[1]);
                    funcionario.setRg(fields[2]);
                    funcionario.setDataNascimento(Util.dateFormatter(fields[3]));
                    funcionario.setSexo(fields[4]);
                    funcionario.setEstadoCivil(fields[5]);
                    funcionario.setTelefone(fields[6]);
                    funcionario.setEmail(fields[7]);
                    funcionario.setCargo(fields[9]);
                    funcionario.setSalario(new BigDecimal(fields[10]));
                    funcionario.setBonus(new BigDecimal(fields[11]));
                    funcionario.setCepConsulta(fields[8]);

                    try {
                        funcionarioService.incluir(funcionario);
                        System.out.println("  [OK] Funcionário " + funcionario.getNome() + " incluído com sucesso.");
                    } catch (FuncionarioInvalidoException e) {
                        System.err.println("  [ERRO] Problema na inclusão do funcionário " + funcionario.getNome() + ": " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("[ERRO] Problema ao ler o arquivo funcionário.txt: " + e.getMessage());
        }

        System.out.println("[FuncionarioLoader] Carregamento concluído.");

        System.out.println("--- Funcionários Carregados ---");
        funcionarioService.obterLista().forEach(System.out::println);
        System.out.println("-----------------------------");
    }

}
