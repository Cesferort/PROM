package com.example.juego;

import android.graphics.Canvas;

public class GameLoopThread  extends Thread{
    static final long FPS=10;
    private VistaJuego view;
    private boolean running = false;

    public GameLoopThread (VistaJuego view){
        this.view = view;
    }

    public void setRunning (boolean run) {
        running = run;
    }

    @Override
    public void run() {

        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running){
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c=view.getHolder().lockCanvas();
                synchronized (view.getHandler()){
                    view.onDraw(c);
                }
            }finally {
                if (c!=null){
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() -startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            }catch (Exception e){}

        }
    }
}
