package org.terasology.rendering.gui.windows;

import org.terasology.asset.Assets;
import org.terasology.config.Config;
import org.terasology.game.CoreRegistry;
import org.terasology.rendering.gui.framework.UIDisplayElement;
import org.terasology.rendering.gui.framework.events.ClickListener;
import org.terasology.rendering.gui.widgets.UIButton;
import org.terasology.rendering.gui.widgets.UIImage;
import org.terasology.rendering.gui.widgets.UILabel;
import org.terasology.rendering.gui.widgets.UIWindow;

import javax.vecmath.Vector2f;

/**
 * Created with IntelliJ IDEA.
 * User: Fred
 * Date: 4/17/13
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class UIMenuSettings extends UIWindow {

    final UIImage title;
    final UILabel version;

    private final UIButton backToMainMenuButton;
    private final UIButton videoButton;
    private final UIButton audioButton;
    private final UIButton controlsButton;
    private final UIButton advancedButton;

    public UIMenuSettings() {
        setId("configer");
        setBackgroundImage("engine:loadingbackground");
        setModal(true);
        maximize();

        title = new UIImage(Assets.getTexture("engine:terasology"));
        title.setHorizontalAlign(EHorizontalAlign.CENTER);
        title.setPosition(new Vector2f(0f, 128f));
        title.setVisible(true);
        title.setSize(new Vector2f(512f, 128f));

        version = new UILabel("Settings");
        version.setHorizontalAlign(EHorizontalAlign.CENTER);
        version.setPosition(new Vector2f(0f, 230f));
        version.setVisible(true);

        videoButton = new UIButton(new Vector2f(256f, 32f), UIButton.ButtonType.NORMAL);
        videoButton.getLabel().setText("Video");
        videoButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        videoButton.setPosition(new Vector2f(0f, 300f));
        videoButton.setVisible(true);
        videoButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                getGUIManager().openWindow("config:video");
            }
        });

        audioButton = new UIButton(new Vector2f(256f, 32f), UIButton.ButtonType.NORMAL);
        audioButton.getLabel().setText("Audio");
        audioButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        audioButton.setPosition(new Vector2f(0f, 300f + 40f));
        audioButton.setVisible(true);
        audioButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                getGUIManager().openWindow("config:audio");
            }
        });

        controlsButton = new UIButton(new Vector2f(256f, 32f), UIButton.ButtonType.NORMAL);
        controlsButton.getLabel().setText("Controls");
        controlsButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        controlsButton.setPosition(new Vector2f(0f, 300f + 2 * 40f));
        controlsButton.setVisible(true);
        controlsButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                getGUIManager().openWindow("config:controls");
            }
        });

        advancedButton = new UIButton(new Vector2f(256f, 32f), UIButton.ButtonType.NORMAL);
        advancedButton.getLabel().setText("Advanced");
        advancedButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        advancedButton.setPosition(new Vector2f(0f, 300f + 3 * 40f));
        advancedButton.setVisible(true);
        advancedButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                getGUIManager().openWindow("config:advanced");
            }
        });

        backToMainMenuButton = new UIButton(new Vector2f(256f, 32f), UIButton.ButtonType.NORMAL);
        backToMainMenuButton.getLabel().setText("Return to Main Menu");
        backToMainMenuButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        backToMainMenuButton.setPosition(new Vector2f(0f, 300f + 7 * 40f));
        backToMainMenuButton.setVisible(true);
        backToMainMenuButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                CoreRegistry.get(Config.class).save();
                getGUIManager().openWindow("pause");
            }
        });

        addDisplayElement(title);
        addDisplayElement(version);

        addDisplayElement(videoButton);
        addDisplayElement(audioButton);
        addDisplayElement(controlsButton);
        addDisplayElement(advancedButton);
        addDisplayElement(backToMainMenuButton);
    }
}