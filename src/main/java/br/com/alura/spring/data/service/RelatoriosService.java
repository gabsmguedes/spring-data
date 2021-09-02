package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Cargo;
import br.com.alura.spring.data.model.Funcionario;
import br.com.alura.spring.data.model.FuncionarioProjecao;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final FuncionarioRepository funcionarioRepository;
    private Boolean system = true;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario por nome");
            System.out.println("2 - Busca funcionario por nome, salario maior que e data contratação");
            System.out.println("3 - Busca funcionario por data contratação maior que");
            System.out.println("4 - Busca funcionario por salario");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscarFuncionarioPorNome(scanner);
                    break;
                case 2:
                    buscarFuncionarioPorNomeSalarioDataContratacao(scanner);
                    break;
                case 3:
                    buscarFuncionarioPorDataContratacaoMaior(scanner);
                    break;
                case 4:
                    buscarFuncionarioPorSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscarFuncionarioPorNome(Scanner scanner) {
        System.out.println("Qual funcioario você quer buscar?");
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(scanner.next());
        funcionarios.forEach(System.out::println);
    }

    private void buscarFuncionarioPorNomeSalarioDataContratacao(Scanner scanner){
        Funcionario funcionario = new Funcionario();
        System.out.println("Qual o nome que desejar buscar?");
        funcionario.setNome(scanner.next());

        System.out.println("Qual é o salario?");
        funcionario.setSalario(scanner.nextBigDecimal());

        System.out.println("Qual é a data de contratação?");
        funcionario.setDataContratacao(LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        List<Funcionario> funcionarios = funcionarioRepository.findByNomeSalarioMaiorDataContratacao(funcionario.getNome(), funcionario.getSalario(), funcionario.getDataContratacao());
        funcionarios.forEach(System.out::println);
    }

    private void buscarFuncionarioPorDataContratacaoMaior(Scanner scanner){
        System.out.println("Qual é a data da contratação?");
        List<Funcionario> funcionarios = funcionarioRepository.findByDataContratacaoMaior(LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        funcionarios.forEach(System.out::println);
    }

    private void buscarFuncionarioPorSalario() {
        List<FuncionarioProjecao> funcionarioProjecoes = funcionarioRepository.findFuncionarioBySalario();
        funcionarioProjecoes.forEach(f -> System.out.println("Funcionario: id: " + f.getId() +
                " | Nome: " + f.getNome() + " | Salario: " + f.getSalario()));
    }
}
