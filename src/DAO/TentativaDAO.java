package DAO;

import DAO.mysql.generatedclasses.tables.records.TentativaRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Alternativa;
import Modelo.Tentativa;

import java.sql.SQLException;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Tentativa.TENTATIVA;

/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class TentativaDAO extends DAO<Tentativa> {

    private static final TentativaDAO instance = new TentativaDAO();

    @Override
    public boolean novo(Tentativa novo) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Não suportado.");
    }

    public boolean novo(Tentativa novo, int idBloco) throws SQLException, ClassNotFoundException {

        EnunciadoDAO.getInstance().novo(novo.getEnunciado());

        TentativaRecord nova = GerenciadorBD.getContext().insertInto(TENTATIVA, TENTATIVA.IDBLOCO, TENTATIVA.IDENUNCIADO)
                                            .values(idBloco, novo.getEnunciado().getId())
                                            .returning().fetchOne();

        if(nova != null){
            novo.setId(nova.getIdtentativa());

            for(Alternativa alternativa : novo.getAlternativas()){
                AlternativaDAO.getIntance().novo(alternativa, novo.getId());
            }

            return (true);
        }

        return (false);
    }

    @Override
    public Tentativa obter(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Collection<Tentativa> obterTodos() {
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
    public boolean atualizar(Tentativa atualizado) {
        return false;
    }

    public static TentativaDAO getInstance(){
        return (instance);
    }
}
