package DAO;

import DAO.mysql.generatedclasses.tables.records.AvaliacaoRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Avaliacao;
import Modelo.Bloco;
import Modelo.Construtores.AvaliacaoBuilder;
import org.jooq.Result;

import static DAO.mysql.generatedclasses.tables.Avaliacao.AVALIACAO;
import static DAO.mysql.generatedclasses.tables.AvaliacaoBloco.AVALIACAO_BLOCO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gustavo Freitas on 30/10/2015.
 */
public class AvaliacaoDAO extends DAO<Avaliacao> {
    @Override
    public boolean novo(Avaliacao novo) throws SQLException, ClassNotFoundException {

        AvaliacaoRecord nova = GerenciadorBD.getContext().insertInto(AVALIACAO, AVALIACAO.DESCRICAO)
                                                         .values(novo.getDescricao())
                                                         .returning().fetchOne();

        if(nova != null){
            novo.setId(nova.getIdavaliacao());

            for(Bloco b : novo.getBlocos()){
                if(!BlocoDAO.getInstance().novo(b)){
                    System.out.println("Erro ao gravar bloco. " + b);
                }
                if(GerenciadorBD.getContext().insertInto(AVALIACAO_BLOCO, AVALIACAO_BLOCO.IDAVALIACAO, AVALIACAO_BLOCO.IDBLOCO)
                                          .values(novo.getId(), b.getId())
                                          .returning().fetchOne() == null){
                    System.out.println("Erro ao associar bloco com a avaliação. " + b);
                }
            }

            return (true);
        }

        return false;
    }

    @Override
    public Avaliacao obter(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Collection<Avaliacao> obterTodos() {
        ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

        Result<AvaliacaoRecord> resultado = GerenciadorBD.getContext().selectFrom(AVALIACAO).fetch();

        AvaliacaoBuilder builder = new AvaliacaoBuilder();

        for(AvaliacaoRecord a : resultado){
            builder.setDescricao(a.getDescricao());
            builder.setId(a.getIdavaliacao());
            avaliacoes.add(builder.getInstance());
        }

        return (avaliacoes);
    }

    @Override
    public boolean apagar(int id) {
        return false;
    }

    @Override
    public boolean apagarEmCascata(int id) {
        return false;
    }

    @Override
    public boolean atualizar(Avaliacao atualizado) {
        return false;
    }

    public static AvaliacaoDAO getInstance(){
        return (new AvaliacaoDAO());
    }
}
