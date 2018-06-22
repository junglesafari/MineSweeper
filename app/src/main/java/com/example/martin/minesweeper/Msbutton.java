package com.example.martin.minesweeper;

import android.content.Context;

public class Msbutton extends android.support.v7.widget.AppCompatButton {
    public int mine = 0;
    public int count = 0;
    public int shownumber;
    public int indexi;
    public int indexj;
    public boolean open = false;
    public boolean checkflag = true;
    public boolean longclick = true;
    public  boolean fff=false;

    public Msbutton(Context context) {
        super( context );
    }

    public void showvalue(int size, Msbutton[][] board, int indexi1, int indexj1) {
        if (mine == -1) {

            MainActivity.loosegame = true;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Msbutton b = board[i][j];
                    if (b.mine == -1) {
                        b.setBackgroundDrawable( getResources().getDrawable( R.drawable.bomb ) );
                    }
                    if (b.count == 0 && b.mine == 0) {
                        b.setBackground( getResources().getDrawable( R.drawable.backcolor2 ) );
                    }
                    if (b.count != 0 && b.mine == 0) {
                        b.setText( b.count + " " );
                    }
                }
            }
        } else {
            if (count != 0) {
                this.setText( count + "" );
            }
        }
        if (count == 0) {
            checkflag = false;
            setBackground( getResources().getDrawable( R.drawable.backcolor2 ) );
            for (int i = 0; i < MainActivity.ai.length; i++) {
                if (indexi1 + MainActivity.ai[i] < size && indexj1 + MainActivity.aj[i] < size && indexi1 + MainActivity.ai[i] >= 0 && indexj1 + MainActivity.aj[i] >= 0) {
                    Msbutton sbutton = board[indexi1 + MainActivity.ai[i]][indexj1 + MainActivity.aj[i]];

                    if (sbutton.count == 0) {


                        if(!sbutton.fff){
                            sbutton.fff=true;
                            MainActivity.count++;}
                        if (sbutton.checkflag) {

                           // sbutton.fff=true;

                            sbutton.showvalue( size, board, sbutton.indexi, sbutton.indexj );
                        }
                    } else {
                        if(!sbutton.fff){
                            sbutton.fff=true;
                        MainActivity.count++;
                        if(MainActivity.count==MainActivity.reqcount){
                            MainActivity m=new MainActivity();
                            m.youwin();
                        }}
                        sbutton.setText( sbutton.count + "" );

                    }

                }
            }

        }
    }




}