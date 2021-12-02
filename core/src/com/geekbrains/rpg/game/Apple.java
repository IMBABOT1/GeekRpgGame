package com.geekbrains.rpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Apple {

    private Vector2 position;
    private TextureRegion textureRegion;
    private boolean active;
    private GeekRpgGame game;
    private Vector2 temp;

    public Apple(TextureAtlas atlas, GeekRpgGame game){
        this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        this.textureRegion = atlas.findRegion("Apple");
        this.active = true;
        this.game = game;
        this.temp = new Vector2(0, 0);
    }


    public void render(SpriteBatch batch){
        if (active) {
            batch.draw(textureRegion, position.x, position.y);
        }else {
            position.x = MathUtils.random(0, 1280);
            position.y = MathUtils.random(0, 720);
            active = true;
        }
    }

    private void deactivate(){
        active = false;
    }

    public void update(float dt) {
        if (position.x < 0 || position.x > 1280 || position.y < 0 || position.y > 720){
            position.x = MathUtils.random(0, 1280);
            position.y = MathUtils.random(0, 720);
        }

        float projectileX = game.getHero().getProjectile().getPosition().x;
        float projectileY = game.getHero().getProjectile().getPosition().y;

        if (position.dst(projectileX, projectileY) < 30){
           deactivate();
        }
    }
}
