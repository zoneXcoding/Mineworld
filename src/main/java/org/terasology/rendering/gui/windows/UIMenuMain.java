/*
 * Copyright 2012 Benjamin Glatzel <benjamin.glatzel@me.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.rendering.gui.windows;

import javax.vecmath.Vector2f;

import org.newdawn.slick.Color;
import org.terasology.asset.Assets;
import org.terasology.game.CoreRegistry;
import org.terasology.game.GameEngine;
import org.terasology.rendering.gui.framework.UIDisplayElement;
import org.terasology.rendering.gui.framework.events.ClickListener;
import org.terasology.rendering.gui.widgets.UIButton;
import org.terasology.rendering.gui.widgets.UIImage;
import org.terasology.rendering.gui.widgets.UILabel;
import org.terasology.rendering.gui.widgets.UIWindow;

/**
 * Main menu screen.
 *
 * @author Anton Kireev <adeon.k87@gmail.com>
 */
public class UIMenuMain extends UIWindow {

    private final UIImage _title;

    private final UIButton _exitButton;
    private final UIButton _singlePlayerButton;
    private final UIButton _configButton;
    private final UIButton _multiPlayerButton;

    final UILabel _version;
    final UILabel _copyright;

    public UIMenuMain() {
        setId("main");
        setBackgroundImage("engine:menubackground");
        setModal(true);
        maximize();
        
        _title = new UIImage(Assets.getTexture("engine:terasology"));
        _title.setSize(new Vector2f(512f, 128f));
        _title.setHorizontalAlign(EHorizontalAlign.CENTER);
        _title.setVerticalAlign(EVerticalAlign.TOP);
        _title.setVisible(true);

        _version = new UILabel("Pre Alpha");
        //_version.setHorizontalAlign(EHorizontalAlign.CENTER);
        _version.setVerticalAlign(EVerticalAlign.BOTTOM);
        //_version.setPosition(new Vector2f(0f, 230f));
        _version.setVisible(true);
        _version.setTextShadow(true);

        _copyright = new UILabel("Copyright TheConvergenceProject | Do not redistribute!");
        _copyright.setVerticalAlign(EVerticalAlign.BOTTOM);
        _copyright.setHorizontalAlign(EHorizontalAlign.RIGHT);
        _copyright.setVisible(true);
        _copyright.setTextShadow(true);
        _copyright.setTextShadowColor(Color.magenta);

        _exitButton = new UIButton(new Vector2f(128f, 32f), UIButton.ButtonType.NORMAL);
        _exitButton.getLabel().setText("Exit Mineworld");
        _exitButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                CoreRegistry.get(GameEngine.class).shutdown();
            }
        });
        _exitButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        _exitButton.setPosition(new Vector2f(0 - 190f, 300f + 4 * 40f));
        _exitButton.setVisible(true);
        
        _configButton = new UIButton(new Vector2f(128f, 32f), UIButton.ButtonType.NORMAL);
        _configButton.getLabel().setText("Settings");
        _configButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                getGUIManager().openWindow("config");
            }
        });
        _configButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        _configButton.setPosition(new Vector2f(190f, 300f + 4 * 40f));
        _configButton.setVisible(true);

        _singlePlayerButton = new UIButton(new Vector2f(512f, 32f), UIButton.ButtonType.NORMAL);
        _singlePlayerButton.getLabel().setText("Single player");
        _singlePlayerButton.addClickListener(new ClickListener() {
            @Override
            public void click(UIDisplayElement element, int button) {
                getGUIManager().openWindow("singleplayer");
            }
        });

        _singlePlayerButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        _singlePlayerButton.setPosition(new Vector2f(0f, 300f + 40f));
        _singlePlayerButton.setVisible(true);

        _multiPlayerButton = new UIButton(new Vector2f(512f, 32f), UIButton.ButtonType.NORMAL);
        _multiPlayerButton.getLabel().setText("Multiplayer");
        _multiPlayerButton.addClickListener(new ClickListener(){
        	@Override
        	public void click(UIDisplayElement element, int button) {
        		getGUIManager().openWindow("multiplayer");
        	}
        });
        
        _multiPlayerButton.setHorizontalAlign(EHorizontalAlign.CENTER);
        _multiPlayerButton.setPosition(new Vector2f(0f, 300f + 2 * 40f));
        _multiPlayerButton.setVisible(true);
        
        addDisplayElement(_title);
        addDisplayElement(_version);
        addDisplayElement(_copyright);
        addDisplayElement(_configButton);
        addDisplayElement(_exitButton);
        addDisplayElement(_singlePlayerButton);
        addDisplayElement(_multiPlayerButton);
    }
}
