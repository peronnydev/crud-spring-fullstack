package br.com.catolica.crud_aluno.Controller;

import br.com.catolica.crud_aluno.Model.Aluno;
import br.com.catolica.crud_aluno.Repository.AlunoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class ApiController {

    AlunoRepository alunoRepository = new AlunoRepository();

    @GetMapping("/entidade")
    public Object retornarTodos(){
        return alunoRepository.findAll();
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
        alunoRepository.salvarAluno(aluno);
        return aluno;
    }

    @PutMapping("/atualizar/{uuid}")
    public Object atualizarAluno(@PathVariable UUID uuid, @RequestBody Aluno alunoAtualizado) {
        return alunoRepository.editarAluno(uuid, alunoAtualizado);
    }
}
