package Visao.Administracao.Componentes;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class ToggleImageButton extends ToggleButton {
    public ToggleImageButton(String imgSrc, String text, double width, double height){
        super(text);

        if(imgSrc != null && imgSrc.length() > 0){
            ImageView image = new ImageView("file:////" + imgSrc);
            image.setFitHeight(height);
            image.setFitWidth(width);
            image.setPreserveRatio(true);
            image.setSmooth(true);
            image.setCache(true);
            this.setGraphic(image);
        }

        setWidth(width + 5);
        setHeight(height + 5);
    }

}
