package fr.utt.isi.lo02.unoGame;

import com.badlogic.gdx.backends.lwjgl.*;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;

public class TesteurGUI {
    public static void main(String[] args){
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "UNO Game";
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;
        new LwjglApplication(new GuiMasterView(), cfg);
    }
}
