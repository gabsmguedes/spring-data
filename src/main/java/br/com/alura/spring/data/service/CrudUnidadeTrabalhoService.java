package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Cargo;
import br.com.alura.spring.data.model.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private Boolean system = true;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de unidade de trabalho deseja executar?");
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
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

        System.out.println("Descriçao da unidade de trabalho");
        unidadeTrabalho.setDescricao(scanner.next());

        System.out.println("Qual o endereço");
        unidadeTrabalho.setEndereco(scanner.next());

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de trabalho foi salvo com sucesso!");
    }

    private void atualizar(Scanner scanner) {
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

        System.out.println("Id");
        unidadeTrabalho.setId(scanner.nextInt());

        System.out.println("Nova descrição da unidade de trabalho");
        unidadeTrabalho.setDescricao(scanner.next());

        System.out.println("Nova endereço");
        unidadeTrabalho.setEndereco(scanner.next());

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Alterado com sucesso");
    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
        unidadeTrabalhos.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        unidadeTrabalhoRepository.deleteById(scanner.nextInt());
        System.out.println("Deletado com sucesso");
    }

}
