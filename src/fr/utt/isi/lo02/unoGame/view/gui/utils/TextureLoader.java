package fr.utt.isi.lo02.unoGame.view.gui.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureLoader {
    public final static Texture TEXTURE_CARD_PATTERN = new Texture(Gdx.files.internal("ressources/img/card/elements/cardPattern.png"));
    public final static Texture TEXTURE_CARD_PATTERN_FLIPPED = new Texture(Gdx.files.internal("ressources/img/card/elements/cardPatternBack.png"));
    public final static Texture TEXTURE_PLAYER_AVATAR_DEFAULT = new Texture(Gdx.files.internal("ressources/img/player/player.png"));
}
