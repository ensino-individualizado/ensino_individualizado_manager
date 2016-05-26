/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.AlunoRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Aluno;
import Modelo.Turma;
import org.jooq.Record;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Aluno.ALUNO;
import static DAO.mysql.generatedclasses.tables.Realiza.REALIZA;



/**
 *
 * @author Gustavo Freitas
 */
public class AlunoDAO extends DAO<Aluno>{

    private static final AlunoDAO intancia = new AlunoDAO();
    @Override
    public boolean novo(Aluno novo) {

        AlunoRecord aluno = GerenciadorBD.getContext().insertInto(ALUNO, ALUNO.NOME, ALUNO.NASCIMENTO, ALUNO.TUEMA_IDTURMA)
                                  .values(novo.getNome(), new java.sql.Date(novo.getDataNascimento().getTime()), novo.getTurma().getId())
                                  .returning().fetchOne();

        if(aluno != null){
            novo.setId(aluno.getIdaluno());
            return (true);
        }
        
        return (false);
    }

    @Override
    public Aluno obter(int id) {

        Aluno aluno = null;
        
        AlunoRecord resultado = GerenciadorBD.getContext().selectFrom(ALUNO).where(ALUNO.IDALUNO.eq(id)).fetchOne();
        
        if(resultado != null){
            Turma turma = TurmaDAO.getInstance().obter(resultado.getTuemaIdturma());
            aluno = new Aluno(resultado.getIdaluno(), resultado.getNome(), resultado.getNascimento(), turma);
        }
        
        return (aluno);
    }

    @Override
    public Collection<Aluno> obterTodos() {

        ArrayList<Aluno> alunos = new ArrayList<>();
        
        Result<AlunoRecord> resultado = GerenciadorBD.getContext().selectFrom(ALUNO).fetch();
        
        for(AlunoRecord a : resultado){
            Turma turma = TurmaDAO.getInstance().obter(a.getTuemaIdturma());
            alunos.add(new Aluno(a.getIdaluno(), a.getNome(), a.getNascimento(), turma));
        }
        
        return (alunos);
    }

    @Override
    public boolean apagar(int id) {
        
        Record aluno = GerenciadorBD.getContext().select(ALUNO.IDALUNO)
                                                 .from(ALUNO).join(REALIZA).on(ALUNO.IDALUNO.eq(REALIZA.IDALUNO))
                                                 .where(REALIZA.IDAVALIACAO.eq(id)).fetchOne();
        
        if(aluno == null){
            GerenciadorBD.getContext().deleteFrom(ALUNO).where(ALUNO.IDALUNO.eq(id)).execute();
            return (true);
        }
         
        return (false);
    }


    public Collection<Aluno> obterTodosByIdTurma(int id){
        ArrayList<Aluno> alunos = new ArrayList<>();

        Result<AlunoRecord> resultado = GerenciadorBD.getContext().selectFrom(ALUNO).where(ALUNO.TUEMA_IDTURMA.eq(id)).fetch();

        for(AlunoRecord a : resultado){
            alunos.add(new Aluno(a.getIdaluno(), a.getNome(), a.getNascimento(), TurmaDAO.getInstance().obter(a.getTuemaIdturma())));
        }

        return (alunos);
    }


    @Override
    public boolean apagarEmCascata(int id){
        throw new UnsupportedOperationException("Não suportado.");
    }
    
    @Override
    public boolean atualizar(Aluno aluno) {

        GerenciadorBD.getContext().update(ALUNO)
                                  .set(ALUNO.NOME, aluno.getNome())
                                  .set(ALUNO.TUEMA_IDTURMA, aluno.getTurma().getId())
                                  .set(ALUNO.NASCIMENTO, new java.sql.Date(aluno.getDataNascimento().getTime()))
                                  .where(ALUNO.IDALUNO.eq(aluno.getId()))
                                  .execute();
        
        return (true);
    }
    
    public static AlunoDAO getInstance() {
        return (AlunoDAO.intancia);
    }
}
