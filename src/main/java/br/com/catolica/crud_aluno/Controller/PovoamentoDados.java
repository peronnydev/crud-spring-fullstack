package br.com.catolica.crud_aluno.Controller;

import br.com.catolica.crud_aluno.Model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class PovoamentoDados {

    private List<Aluno> listaAluno;
    int numeroDeAlunos;
    public PovoamentoDados(int numeroDeAlunos){
        this.listaAluno = new ArrayList<>();
        this.numeroDeAlunos = numeroDeAlunos;
    }

    public List<Aluno> povoamentoList(){
        for (int i = 0; i < numeroDeAlunos + 1; i++){ // apenas para facilitar os testes
            String curso = "Ciência da Computação";

            listaAluno.add(new Aluno(
                    "aluno" + i,
                    String.format(String.valueOf(i)),
                    curso,
                    17 + (i / 100),
                    "aluno" + i + ".edu@gmail.com",
                    "(83) 9 "+ String.format("%08d", i)
            ));
        }
        return listaAluno;
    }
}
