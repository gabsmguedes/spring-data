package br.com.alura.spring.data.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private BigDecimal salario;
    private LocalDate dataContratacao;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidades", joinColumns = {@JoinColumn(name = "fk_funcionario")},
                inverseJoinColumns = {@JoinColumn(name = "fk_unidade")})
    private List<UnidadeTrabalho> unidadeTrabalhos;

    @Override
    public String toString() {
        return "Funcionario: " + "id:" + id + "| nome:'" + nome + "| cpf:" + cpf + "| salario:" + salario
                + "| dataContratacao:" + dataContratacao + "| cargo:" + cargo.getDescricao();
    }
}
