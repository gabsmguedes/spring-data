package br.com.alura.spring.data.service;

import br.com.alura.spring.data.model.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Digite um nome");
        String nome = scanner.next();
        if(nome.equalsIgnoreCase("null")){
            nome = null;
        }

        System.out.println("Digite um cpf");
        String cpf = scanner.next();
        if(cpf.equalsIgnoreCase("null")){
            cpf = null;
        }

        System.out.println("Digite um salario");
        BigDecimal salario = scanner.nextBigDecimal();
        if(salario.compareTo(salario) == 0){
            salario = null;
        }

        System.out.println("Digite um data contracao");
        String dataString = scanner.next();
        LocalDate dataContratacao;
        if(dataString.equalsIgnoreCase("null")){
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.
                where(SpecificationFuncionario.nome(nome))
                .or(SpecificationFuncionario.cpf(cpf))
                .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                .or(SpecificationFuncionario.salario(salario))
                );
        funcionarios.forEach(System.out::println);
    }

}
