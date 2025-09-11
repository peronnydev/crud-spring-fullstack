package br.com.catolica.crud_aluno.Repository;

import br.com.catolica.crud_aluno.Model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class AlunoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlunoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Aluno> findAll() {
        String sql = """
                SELECT * FROM aluno WHERE ativo = true
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Aluno aluno = new Aluno();
            aluno.setId(UUID.fromString(rs.getString("id")));
            aluno.setNome(rs.getString("nome"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setCurso(rs.getString("curso"));
            aluno.setIdade(rs.getInt("idade"));
            aluno.setEmail(rs.getString("email"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setAtivo(rs.getBoolean("ativo"));
            return aluno;
        });
    }

    public Object salvarAluno(Aluno aluno){
        String sql = """
                INSERT INTO aluno (id, nome, matricula, curso, idade, email, telefone)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        aluno.setId(UUID.randomUUID());
        int atributos = jdbcTemplate.update(sql,
                aluno.getId(),
                aluno.getNome(),
                aluno.getMatricula(),
                aluno.getCurso(),
                aluno.getIdade(),
                aluno.getEmail(),
                aluno.getTelefone());
        return atributos > 1;
    }

    public Object removerAluno(UUID uuid){
        String sql = """
                UPDATE aluno SET ativo = false WHERE id = ?
                """;
        int atributos = jdbcTemplate.update(sql, uuid);
        return atributos > 0;
    }

    public Object editarAluno(UUID id, Aluno aluno){
        String sql = """
                UPDATE aluno SET nome = ?, matricula = ?, curso = ?, idade = ?, email = ?, telefone = ? WHERE id = ?
                """;
        int atributos = jdbcTemplate.update(sql,
                aluno.getNome(),
                aluno.getMatricula(),
                aluno.getCurso(),
                aluno.getIdade(),
                aluno.getEmail(),
                aluno.getTelefone(),
                id);
        return atributos > 0;
    }

    public Object pesquisarNomeAluno(String nome){
        String sql = """
                SELECT * FROM aluno WHERE nome ILIKE ? AND ativo = true
                """;
        String nomeBusca = "%" + nome + "%";
        return jdbcTemplate.query(sql, new Object[]{nomeBusca}, (rs, rowNum) -> {
            Aluno aluno = new Aluno();
            aluno.setId(UUID.fromString(rs.getString("id")));
            aluno.setNome(rs.getString("nome"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setCurso(rs.getString("curso"));
            aluno.setIdade(rs.getInt("idade"));
            aluno.setEmail(rs.getString("email"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setAtivo(rs.getBoolean("ativo"));
            return aluno;
        });
    }
}