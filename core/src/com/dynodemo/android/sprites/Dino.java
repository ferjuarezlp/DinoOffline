package com.dynodemo.android.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ferjuarez on 24/03/16.
 */

public class Dino {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 150;
    private Vector3 position;
    private Vector3 velocity;
    private Texture dino;
    private Rectangle bounds;
    private Animation dinoAnimation;
    private Sound jump;
    private boolean onGround = true;

    public Dino(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        dino = new Texture("dino.png");
        Texture texture = new Texture("dinoanim.png");
        dinoAnimation = new Animation(new TextureRegion(texture), 6, 0.5f);
        bounds = new Rectangle(x,y,texture.getWidth() / 3, texture.getHeight());
        jump = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
    }

    public void update(float dt){
        dinoAnimation.update(dt);
        if(position.y > 85.0f){
            onGround = false;
            velocity.add(0, GRAVITY, 85.0f);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        if(position.y < 85.0f){
            position.y = 85.0f;
            onGround = true;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public TextureRegion getTexture() {
        return dinoAnimation.getFrame();
    }


    public void startJump(){
        if(onGround){
            jump.play();
            velocity.y += 250;
            onGround = false;
        }
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        dino.dispose();
        jump.dispose();
    }


}
