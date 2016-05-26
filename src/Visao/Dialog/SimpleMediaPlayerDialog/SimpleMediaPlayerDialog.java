package Visao.Dialog.SimpleMediaPlayerDialog;

import Visao.Administracao.FxmlReference;
import Visao.ControllerHierarchy.WindowController;
import Visao.Dialog.SimpleDialog;

import java.io.IOException;

/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class SimpleMediaPlayerDialog extends SimpleDialog {

    public SimpleMediaPlayerDialog(WindowController parent, String fileName, String src) throws IOException {

        super(parent, "Media Player - " + fileName, null, FxmlReference.getReference("simple_media_player"));

        ((SimpleMediaPlayerController)this.getController()).setMedia(src);
        this.setWidth(((SimpleMediaPlayerController) this.getController()).getPlayer().getMedia().getWidth() + 30);
        this.setHeight(((SimpleMediaPlayerController) this.getController()).getPlayer().getMedia().getHeight() + 90);
        this.setResizable(false);

        this.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
            ((SimpleMediaPlayerController) this.getController()).stop();
            close();
        });

        ((SimpleMediaPlayerController) this.getController()).getPlayer().setOnReady(() -> {
            setWidth(((SimpleMediaPlayerController) getController()).getPlayer().getMedia().getWidth());
            setHeight(((SimpleMediaPlayerController) getController()).getPlayer().getMedia().getHeight());
        });
    }

    public void showWaitAndPlay(){
        ((SimpleMediaPlayerController)this.getController()).play();
        this.showAndWait();
    }
}
