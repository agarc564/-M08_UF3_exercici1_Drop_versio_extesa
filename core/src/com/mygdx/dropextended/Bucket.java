package com.mygdx.dropextended;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bucket extends Actor {

    // Diferents posicions de l'Spacecraft: recta, pujant i baixant
    public static final int BUCKET_CENTER = 0;
    public static final int BUCKET_RIGHT = 1;
    public static final int BUCKET_LEFT = 2;

    private int width, height;
    private Vector2 position;
    Texture bucketImage;
    private Rectangle collisionRect;
    private int direction;

    public Bucket(float x, float y, int width, int height) {

    // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        // Inicialitzem EL CUBELL a l'estat normal
        direction = BUCKET_CENTER;

        //Creem el rectangle de col·lisions
        collisionRect = new Rectangle();

        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(bucketImage, position.x, position.y, width, height);
    }

    @Override
    public void act (float delta){
        // Movem l'Spacecraft depenent de la direcció controlant que no surti de la pantalla
        /*switch (direction) {
            case BUCKET_LEFT:
                this.position.x -= 200 * Gdx.graphics.getDeltaTime();
                break;
            case BUCKET_RIGHT:
                this.position.x += 200 * Gdx.graphics.getDeltaTime();
                break;
            case BUCKET_CENTER:
                break;
        }
        collisionRect.set(position.x, position.y, width, height);*/
        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //camera.unproject(touchPos);
            this.position.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            this.position.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            this.position.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if (this.position.x < 0)
            this.position.x = 0;
        if (this.position.x > 800 - 64)
            this.position.x = 800 - 64;
    }

    // Canviem la direcció de l'Spacecraft: Puja
    public void goLeft() {
        direction = BUCKET_LEFT;
    }

    // Canviem la direcció de l'Spacecraft: Baixa
    public void goRight() {
        direction = BUCKET_RIGHT;
    }

    // Posem l'Spacecraft al seu estat original
    public void goCenter() {
        direction = BUCKET_CENTER;
    }

    // Getters dels atributs principals
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
