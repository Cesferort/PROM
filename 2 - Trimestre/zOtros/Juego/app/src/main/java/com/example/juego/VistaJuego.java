package com.example.juego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VistaJuego  extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private List<TempSprite> temps = new ArrayList<TempSprite>();
    private long lastClick;
    private Bitmap bmpBlood;
    private int idExplosion;
    private SoundPool soundPool;

    //Para el control de final de juego
    private boolean gameOver = false;

    private int malos=0, score=0;

    private int tiempo=0;
    private int tiempoNuevoMalo=40;
    private int maxMalos=40;
    private final static int MALOS_INICIALES=10;


    public VistaJuego(Context context) {
        super(context);

        //Cargamos sonido en memoria
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        idExplosion = soundPool.load(this.getContext(),R.raw.explosion, 0);

        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
                createSprites();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {

                    }
                }
            }
        });

        //bmp = BitmapFactory.decodeResource(getResources(), R.drawable.princesa);
        //sprite= new Sprite(this,bmp);
        bmpBlood = BitmapFactory.decodeResource(getResources(),R.drawable.blood1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        for (Sprite sprite:sprites)
            sprite.onDraw(canvas);

        for (int i = temps.size() - 1; i >= 0; i--){
            temps.get(i).onDraw(canvas);
        }

        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setTextSize(canvas.getWidth() / 10);
        p.setTextAlign(Paint.Align.RIGHT);
        String text = "Score: "+ score;
        Log.i("probando score", score+"");
        canvas.drawText(text, canvas.getWidth(), (float)(canvas.getHeight() * 0.1 ),p);
        text = "Malos: "+ malos;
        Log.i("PROBANDO MALOS:", malos+"");
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(text, 0, (float)(canvas.getHeight()*0.1), p);


        //Creamos un nuevo malo cada tiempoNuevoMalo FPS y cuantos más
        //puntos tengamos más rápido se generaran
        tiempo++;
        if (tiempo > tiempoNuevoMalo){
            int nuevosMalos = Math.round(score / 10)+ 1;
            for (int i = 0; i < nuevosMalos; i++)
                crearMalo();
        }

        if (malos > maxMalos)
            gameOver = true;

        //Información de final de partida que aparece en pantalla
        if (gameOver){
            p.setTextSize(canvas.getWidth()/7);
            p.setTextAlign(Paint.Align.CENTER);
            text = "Game Over";
            canvas.drawText(text, canvas.getWidth()/2,
                    canvas.getHeight()/4, p);
            p.setTextSize(canvas.getWidth() /10);
            text = "Toca la pantalla";
            canvas.drawText (text, canvas.getWidth()/2,
                    canvas.getHeight()/4 +(float)(canvas.getHeight()*0.3)/2, p);
            text = "para salir";
            canvas.drawText (text, canvas.getWidth()/2,
                    canvas.getHeight()/4 +(float)(canvas.getHeight()*0.3),p);
            gameLoopThread.setRunning(false);
        }
    }

    private void crearMalo() {
        Random rnd = new Random();
        switch (rnd.nextInt(4)) {
            case 0:
                sprites.add(createSprite(R.drawable.malo));
                break;
            case 1:
                sprites.add(createSprite(R.drawable.malo2));
                break;
            case 2:
                sprites.add(createSprite(R.drawable.malo3));
                break;
            case 3:
                sprites.add(createSprite(R.drawable.malo4));
                break;
        }
        tiempo = 0;
        malos ++;
    }

    private Sprite createSprite(int resouce) {
        bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this, bmp);
    }

    /*private void createSprites(){
        sprites.add(createSprite(R.drawable.princesa));
        sprites.add(createSprite(R.drawable.malo));
        sprites.add(createSprite(R.drawable.malo));
        sprites.add(createSprite(R.drawable.malo2));
        sprites.add(createSprite(R.drawable.malo2));
        sprites.add(createSprite(R.drawable.malo3));
        sprites.add(createSprite(R.drawable.malo3));
        sprites.add(createSprite(R.drawable.malo4));
        sprites.add(createSprite(R.drawable.malo4));
    }*/

    private void createSprites(){
        //Creamos una princesa y el valor de la cte MALOS_INICIALES sprites malos
        //de manera aleatoria
        sprites.add(createSprite(R.drawable.princesa));

        for (int i = 0; i < MALOS_INICIALES; i++){
            crearMalo();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameOver){
            gameLoopThread.setRunning(false);
            Intent intent =  new Intent(this.getContext(), MainActivity.class);
            intent.putExtra("puntuacion",score+"");
            this.getContext().startActivity(intent);
        }


        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();
            float x = event.getX();
            float y = event.getY();
            synchronized (getHolder()) {
                for (int i = sprites.size() - 1; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);
                    if (sprite.isCollition(event.getX(), event.getY())) {
                        sprites.remove(sprite);
                        temps.add(new TempSprite(temps, this, x, y, bmpBlood));
                        //Para el sonido
                        if (i==0){
                            gameOver=true;
                        }
                        else{
                            soundPool.play(idExplosion, 1,1,0,0,1);
                            malos--;
                        }
                        score++;
                        break;
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
