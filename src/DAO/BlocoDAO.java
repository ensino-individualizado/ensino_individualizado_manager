package DAO;

import DAO.mysql.generatedclasses.tables.records.BlocoRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Bloco;
import Modelo.Tentativa;

import java.sql.SQLException;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Bloco.BLOCO;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class BlocoDAO extends DAO<Bloco> {
    @Override
    public boolean novo(Bloco novo) throws SQLException, ClassNotFoundException {
        BlocoRecord bloco = GerenciadorBD.getContext().insertInto(BLOCO, BLOCO.IDTIPOBLOCO)
                                                      .values(novo.getTipo().getId())
                                                      .returning().fetchOne();

        if(bloco != null){
            novo.setId(bloco.getIdbloco());

            for(Tentativa tentativa : novo.getTentativas()){
                TentativaDAO.getInstance().novo(tentativa, novo.getId());
            }

            return (true);
        }

        return (false);
    }

    @Override
    public Bloco obter(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Collection<Bloco> obterTodos() {
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
    public boolean atualizar(Bloco atualizado) {
        return false;
    }

    public static BlocoDAO getInstance(){
        return (new BlocoDAO());
    }
}
