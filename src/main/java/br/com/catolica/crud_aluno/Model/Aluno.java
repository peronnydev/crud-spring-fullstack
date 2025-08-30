package br.com.catolica.crud_aluno.Model;

import java.util.UUID;

public class Aluno {
    private UUID id;
    private String nome;
    private String matricula;
    private String curso;
    private int idade;
    private String email;
    private String telefone;
    private boolean ativo;

    public Aluno(String nome, String matricula, String curso, int idade, String email, String telefone){
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;
        this.ativo = true; // delecao logica, nunca vamos perder dados de alunos mesmo que eles sejam "removidos"
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public int getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean getAtivo(){
        return ativo;
    }
}
