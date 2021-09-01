package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Funcionario;
import br.com.alura.spring.data.model.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private final CargoRepository cargoRepository;
    private Boolean system = true;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de funcionario deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner) {
        Funcionario funcionario = new Funcionario();

        System.out.println("Nome do funcionario");
        funcionario.setNome(scanner.next());

        System.out.println("CPF");
        funcionario.setCpf(scanner.next());

        System.out.println("Salario");
        funcionario.setSalario(scanner.nextBigDecimal());

        System.out.println("Data da contratação");
        funcionario.setDataContratacao(LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        System.out.println("Digite o cargoId");
        funcionario.setCargo(cargoRepository.findById(scanner.nextInt()).get());

        List<UnidadeTrabalho> unidades = selecionarUnidades(scanner);
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario foi salvo com sucesso!");
    }

    private List<UnidadeTrabalho> selecionarUnidades(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidadeTrabalhos = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            int id = scanner.nextInt();
            if(id != 0 ){
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(id);
                unidadeTrabalhos.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidadeTrabalhos;
    }

    private void atualizar(Scanner scanner) {
        Funcionario funcionario = new Funcionario();

        System.out.println("Id");
        funcionario.setId(scanner.nextInt());

        System.out.println("Novo nome do funcionario");
        funcionario.setNome(scanner.next());

        System.out.println("Novo CPF");
        funcionario.setCpf(scanner.next());

        System.out.println("Novo Salario");
        funcionario.setSalario(scanner.nextBigDecimal());

        System.out.println("Novo Data da contratação");
        funcionario.setDataContratacao(LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        System.out.println("Novo cargoId");
        funcionario.setCargo(cargoRepository.findById(scanner.nextInt()).get());

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario foi salvo com sucesso!");
    }

    private void visualizar() {
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        funcionarioRepository.deleteById(scanner.nextInt());
        System.out.println("Deletado com sucesso");
    }

}
