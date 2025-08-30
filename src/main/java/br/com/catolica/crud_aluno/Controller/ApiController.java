package br.com.catolica.crud_aluno.Controller;

import br.com.catolica.crud_aluno.Model.Aluno;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class ApiController {

    PovoamentoDados p = new PovoamentoDados(15);
    private List<Aluno> listaAluno = p.povoamentoList();

    @GetMapping("/entidade")
    public Object retornarTodos(){
        ArrayList<Aluno> alunosAtivos = new ArrayList<>();
        for(Aluno a : listaAluno){
            if(a.getAtivo()){
                alunosAtivos.add(a);
            }
        }
        return alunosAtivos;
    }

    @GetMapping("/entidade/removidos")
    public Object retornarRemovidos(){ // nao vou usar mas pode ser util, sempre bom ter
        ArrayList<Aluno> alunosRemovidos = new ArrayList<>();
        for (Aluno a : listaAluno){
            if(!a.getAtivo()) alunosRemovidos.add(a);
        }
        return alunosRemovidos;
    }

    @GetMapping("/entidade/{uuid}") // retorna se o valor inseriodo for igual a matricula
    public Object pesquisarAlunoExato(@PathVariable UUID uuid){
        for(Aluno a : listaAluno){
            if(a.getId().equals(uuid) && a.getAtivo()){
                return a;
            }
        }
        return false;
    }

    @GetMapping("/entidade/busca/{nome}")
    public Object pesquisarNome(@PathVariable String nome){
        ArrayList<Aluno> alunosPesquisados = new ArrayList<>();
        for(Aluno a : listaAluno){
            String nomeAluno = a.getNome().toUpperCase();
            String nomeBusca = nome.toUpperCase();
            if(nomeAluno.contains(nomeBusca)){
                alunosPesquisados.add(a);
            }
        }
        return alunosPesquisados;
    }

    @DeleteMapping("/remover/{uuid}")
    public Object removerAluno(@PathVariable UUID uuid){
        for(Aluno a : listaAluno){
            if(a.getId().equals(uuid)){
                a.setAtivo(false); // delecao logica
                return true;
            }
        }
        return false;
    }

    @PostMapping("/adicionar")
    public Object adicionarAluno(@RequestBody Aluno aluno){
        for(Aluno a : listaAluno){
            if(a.getMatricula().equals(aluno.getMatricula()) || a.getId().equals(aluno.getId())){
                return false;
            }
        }
        listaAluno.add(aluno);
        return true;
    }

    @PutMapping("/atualizar/{uuid}")
    public Object atualizarAluno(@PathVariable UUID uuid, @RequestBody Aluno alunoAtualizado){
        for(Aluno a : listaAluno){
            if(a.getId().equals(uuid)){
                a.setAtivo(alunoAtualizado.getAtivo());
                a.setMatricula(alunoAtualizado.getMatricula());
                a.setCurso(alunoAtualizado.getCurso());
                a.setNome(alunoAtualizado.getNome());
                a.setIdade(alunoAtualizado.getIdade());
                a.setEmail(alunoAtualizado.getEmail());
                a.setTelefone(alunoAtualizado.getTelefone());
                return true;
            }
        }
        return false;
    }
}
