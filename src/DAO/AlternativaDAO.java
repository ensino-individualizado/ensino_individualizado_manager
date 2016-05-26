package DAO;

import DAO.mysql.generatedclasses.tables.records.AlternativaRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Alternativa;

import static DAO.mysql.generatedclasses.tables.Alternativa.ALTERNATIVA;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class AlternativaDAO extends DAO<Alternativa> {

    @Override
    public boolean novo(Alternativa novo) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Não suportado.");
    }

    public boolean novo(Alternativa novo, int idTentativa) throws SQLException, ClassNotFoundException {
        AlternativaRecord nova = GerenciadorBD.getContext().insertInto(ALTERNATIVA, ALTERNATIVA.IDTENTATIVA,
                                                                       ALTERNATIVA.PALAVRA_IDPALAVRA,
                                                                       ALTERNATIVA.PALAVRA_IDCATEGORIA,
                                                                       ALTERNATIVA.PALAVRA_IDAUDIO,
                                                                       ALTERNATIVA.PALAVRA_IDIMAGEM,
                                                                       ALTERNATIVA.RESPOSTA)
                .values(idTentativa, novo.getPalavra().getId(), novo.getPalavra().getCategoria().getId(),
                        novo.getPalavra().getAudio().getId(), novo.getPalavra().getImagem().getId(),
                        (byte) (novo.isResposta() ? 1 : 0))
                .returning().fetchOne();

        if(nova != null){
            novo.setId(nova.getIdalternativa());
            return (true);
        }

        return (false);
    }

    @Override
    public Alternativa obter(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Collection<Alternativa> obterTodos() {
        return null;
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
    public boolean atualizar(Alternativa atualizado) {
        return false;
    }

    public static AlternativaDAO getIntance(){
        return (new AlternativaDAO());
    }
}
