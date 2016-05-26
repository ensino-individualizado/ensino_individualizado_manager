package DAO;

import DAO.mysql.generatedclasses.tables.records.PlaylistRecord;
import Ferramentas.GerenciadorBD;
import Modelo.RecursoDidatico.Midia.Audio;
import Modelo.RecursoDidatico.Midia.Midia;
import Modelo.RecursoDidatico.Midia.Video;
import Modelo.RecursoDidatico.PlayList.PlayList;

import java.sql.SQLException;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.AudioPlaylist.AUDIO_PLAYLIST;
import static DAO.mysql.generatedclasses.tables.Playlist.PLAYLIST;
import static DAO.mysql.generatedclasses.tables.VideoPlaylist.VIDEO_PLAYLIST;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class PlayListDAO extends DAO<PlayList> {
    @Override
    public boolean novo(PlayList novo) throws SQLException, ClassNotFoundException {

        int counter = 0;

        PlaylistRecord playlist = GerenciadorBD.getContext().insertInto(PLAYLIST, PLAYLIST.TIPO)
                .values(novo.getTipo().getId())
                .returning().fetchOne();

        if(playlist != null){
            novo.setId(playlist.getIdplaylist());

            for(Midia midia : novo.getMidias()){
                if(midia instanceof Audio && novo.getTipo().equals(PlayList.TYPE.AUDIO)){
                    if(midia.getId() == -1) {
                        AudioDAO.getInstance().novo((Audio) midia);
                    }
                    GerenciadorBD.getContext().insertInto(AUDIO_PLAYLIST, AUDIO_PLAYLIST.IDAUDIO, AUDIO_PLAYLIST.IDPLAYLIST, AUDIO_PLAYLIST.NUMERO)
                            .values(midia.getId(), novo.getId(), counter).execute();

                }
                else if(midia instanceof Video && novo.getTipo().equals(PlayList.TYPE.VIDEO)){
                    if(midia.getId() == -1) {
                        VideoDAO.getInstance().novo((Video)midia);
                    }
                    GerenciadorBD.getContext().insertInto(VIDEO_PLAYLIST, VIDEO_PLAYLIST.IDVIDEO, VIDEO_PLAYLIST.IDPLAYLIST, VIDEO_PLAYLIST.NUMERO)
                            .values(midia.getId(), novo.getId(), counter);
                }
                else{
                    System.out.println("Playlist heterogênea não é suportada");
                }
                counter++;
            }

            return (true);
        }

        return (false);
    }

    @Override
    public PlayList obter(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Collection<PlayList> obterTodos() {
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
    public boolean atualizar(PlayList atualizado) {
        return false;
    }

    public static PlayListDAO getInstance(){
        return (new PlayListDAO());
    }
}
