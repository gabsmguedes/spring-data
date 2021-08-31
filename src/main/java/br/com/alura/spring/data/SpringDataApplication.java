package br.com.alura.spring.data;

import br.com.alura.spring.data.model.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.CrudCardService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudCardService crudCardService;
    private final CrudFuncionarioService crudFuncionarioService;
    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
    private Boolean system = true;

    public SpringDataApplication(CrudCardService crudCardService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService) {
        this.crudCardService = crudCardService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while(system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Funcionario");
            System.out.println("3 - Unidade Trabalho");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    crudCardService.inicial(scanner);
                    break;
                case 2:
                    crudFuncionarioService.inicial(scanner);
                case 3:
                    crudUnidadeTrabalhoService.inicial(scanner);
                default:
                    system = false;
                    break;
            }
        }
    }
}
