package com.mygdx.dropextended;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Drops extends Actor {

    private int width, height;
    private Vector2 position;
    //Texture bucketImage;
    private Rectangle collisionRect;
    Texture dropImage;

    public Drops(float x, float y, int width, int height) {

        // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        //Creem el rectangle de col·lisions
        collisionRect = new Rectangle();

        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("drop.png"));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.draw(dropImage, position.x, position.y, width, height);
    }


    @Override
    public void act (float delta){
        //super.act(delta);
        this.position.y -= 200 * Gdx.graphics.getDeltaTime();
        if (this.position.y + 64 < 0)
            this.remove();


        //if (this.collisionRect.overlaps(cubell)) {
            //dropsGathered++;
            //dropSound.play();
            //iter.remove();
        //}

    }

    // Retorna true si hi ha col·lisió
    public boolean collides(Bucket cubell) {

        if (position.x <= cubell.getX() + cubell.getWidth()) {
            return (Intersector.overlaps(collisionRect, cubell.getCollisionRectangle()));
        }
        return false;
    }

    public Rectangle getCollisionRectangle() {
        return this.collisionRect;
    }



}
