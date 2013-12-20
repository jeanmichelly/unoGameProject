package fr.utt.isi.lo02.unoGame.view.gui.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Highlight {
    private static String vertexShader =
            "void main()" +
                    "{" +
                    "   gl_FrontColor = gl_Color;" +
                    "   gl_Position = ftransform();" +
                    "}";
    private static String fragmentShader =
            "void main()" +
                    "{" +
                    "   gl_FragColor = gl_Color;" +
                    "}";

    public static final ShaderProgram SHADER = new ShaderProgram(vertexShader, fragmentShader);
}