package br.com.catolica.crud_aluno.Controller;

import br.com.catolica.crud_aluno.Model.Aluno;
import br.com.catolica.crud_aluno.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class ApiController {

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping("/entidade")
    public Object retornarTodos(){
        return alunoRepository.findAll();
    }

    @GetMapping("/entidade/{uuid}")
    public Object pesquisarId(@PathVariable UUID uuid){
        return alunoRepository.pesquisarIdAluno(uuid);
    }


    @GetMapping("/entidade/busca/{nome}")
    public Object pesquisarNome(@PathVariable String nome){
        return alunoRepository.pesquisarNomeAluno(nome);
    }

    @DeleteMapping("/remover/{uuid}")
    public Object removerAluno(@PathVariable UUID uuid){
        return alunoRepository.removerAluno(uuid);
    }

    @PostMapping("/adicionar")
    public Object adicionarAluno(@RequestBody Aluno aluno){
        Aluno novoAluno = new Aluno(
                aluno.getNome(),
                aluno.getMatricula(),
                aluno.getCurso(),
                aluno.getIdade(),
                aluno.getEmail(),
                aluno.getTelefone()
        );
        alunoRepository.salvarAluno(novoAluno);
        return novoAluno;
    }

    @PutMapping("/atualizar/{uuid}")
    public Object atualizarAluno(@PathVariable UUID uuid, @RequestBody Aluno alunoAtualizado) {
        Aluno aluno = new Aluno(
                alunoAtualizado.getNome(),
                alunoAtualizado.getMatricula(),
                alunoAtualizado.getCurso(),
                alunoAtualizado.getIdade(),
                alunoAtualizado.getEmail(),
                alunoAtualizado.getTelefone()
        );
        aluno.setId(uuid);
        aluno.setAtivo(alunoAtualizado.getAtivo());
        return alunoRepository.editarAluno(uuid, aluno);
    }
}
