package com.example.martin.minesweeper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static com.example.martin.minesweeper.highScore.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

   private final String UPDATENAME="UPDATENAME";
   private final String UPDATESCORE="UPDATESCORE";
    public int size = 10;
    public static boolean loosegame = false;
    public static int[] ai = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] aj = {-1, 0, 1, 1, 1, 0, -1, -1};
    LinearLayout rootLayout;
    public static int reqcount;
    public Msbutton[][] board;
    Random rand;
    public static int count;
    ArrayList<LinearLayout> rows;
    TextView texttimer;
    Handler customHnadler;
    public int min=0;
    public int sec=0;
    public int milisec=0;
    long starttime = 0L, timeInMilliSecond = 0L, timeSwapBuff = 0L, updateTime = 0L;
    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliSecond = SystemClock.uptimeMillis() - starttime;
            updateTime = (int) (timeInMilliSecond + timeSwapBuff);
            int secs = (int) (timeInMilliSecond / 1000);
            sec=secs;
            int mins = secs / 60;
            min=mins;
            secs = secs % 60;
            int miliseconds = (int) (timeInMilliSecond % 1000);
            milisec=miliseconds;
            texttimer.setText( " " + mins + ":" + String.format( "%2d", secs ) + ":" + String.format( "%2d", miliseconds ) );
            customHnadler.postDelayed( this, 0 );

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        customHnadler = new Handler();
        setTitle( null );
        rootLayout = findViewById( R.id.rootLayout );
        texttimer = findViewById( R.id.timer );
        Toolbar topToolBar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( topToolBar );

        // setting up timer
        starttime = SystemClock.uptimeMillis();
        customHnadler.postDelayed( updateTimeThread, 0 );


        //

        setBoard();
        setMine( size );
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                checksourrounding( i, j );
            }
        }
    }

    public void setBoard() {
        board = new Msbutton[size][size];
        rows = new ArrayList<>();
        count = 0;
        reqcount = size * size -size;
        rootLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout( this );
            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0, 1 );
            linearLayout.setLayoutParams( linearLayoutParams );
            rootLayout.addView( linearLayout );
            rows.add( linearLayout );
            for (int j = 0; j < size; j++) {
                Msbutton button = new Msbutton( this );
                LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.MATCH_PARENT, 1 );
                buttonParams.setMargins( 2, 2, 2, 2 );
                button.setLayoutParams( buttonParams );
                button.setBackground( getResources().getDrawable( R.drawable.pressed ) );
                button.setOnClickListener( MainActivity.this );
                button.setOnLongClickListener( MainActivity.this );
                LinearLayout row = rows.get( i );
                button.indexi = i;
                button.indexj = j;
                row.addView( button );
                board[i][j] = button;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }
    // smaalkjfdlkjlaksjkld
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reset) {
            loosegame = false;
            setBoard();
            starttime = SystemClock.uptimeMillis();
            customHnadler.postDelayed( updateTimeThread, 0 );
            setMine( size );
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    checksourrounding( i, j );
                }
            }
        } else if (id == R.id.six) {
            size = 6;
            loosegame = false;
            setBoard();
            starttime = SystemClock.uptimeMillis();
            customHnadler.postDelayed( updateTimeThread, 0 );
            setMine( size );
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    checksourrounding( i, j );
                }
            }
        } else if (id == R.id.twelve) {
            size = 12;
            setBoard();
            starttime = SystemClock.uptimeMillis();
            customHnadler.postDelayed( updateTimeThread, 0 );
            loosegame = false;
            setMine( size );
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    checksourrounding( i, j );
                }
            }
        } else if (id == R.id.fifteen) {
            size = 15;
            setBoard();
            starttime = SystemClock.uptimeMillis();
            customHnadler.postDelayed( updateTimeThread, 0 );
            loosegame = false;
            setMine( size );
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    checksourrounding( i, j );
                }
            }
        }


        return super.onOptionsItemSelected( item );

    }

    @Override
    public void onClick(View view) {

        Msbutton msbutton=(Msbutton)view;
        msbutton.fff=true;
        if(!(loosegame)&&msbutton.longclick&&!(msbutton.open)){
            msbutton.open=true;
        msbutton.showvalue(size,board,msbutton.indexi,msbutton.indexj);
       if (loosegame){
           Toast.makeText( MainActivity.this,"you loose"+" "+getIntent().getStringExtra( "name" ),Toast.LENGTH_SHORT ).show();
       }

       count++;


            if(reqcount==count){
                youwin();
            }
       }
        Msbutton msbutton = (Msbutton) view;
        msbutton.fff = true;
        if (!(loosegame) && msbutton.longclick && !(msbutton.open)) {
            msbutton.open = true;
            msbutton.showvalue( size, board, msbutton.indexi, msbutton.indexj );
            if (loosegame) {
                timeSwapBuff += timeInMilliSecond;
                customHnadler.removeCallbacks( updateTimeThread );
                count--;
                Toast.makeText( MainActivity.this, "you loose" + " " + getIntent().getStringExtra( "name" ), Toast.LENGTH_SHORT ).show();
            }

            count++;


            if (reqcount == count) {
                youwin();
            }
        }
    }

    public void setMine(int nummine) {
        if (nummine > 0) {
            rand = new Random();
            int i = rand.nextInt( size );
            int j = rand.nextInt( size );
            Msbutton b = board[i][j];
            if (b.mine == -1) {
                setMine( nummine );
                return;
            }
            b.mine = -1;
            nummine--;
            setMine( nummine );
        } else {
            return;
        }


    }

    @SuppressLint("SetTextI18n")
    public void checksourrounding(int i, int j) {
        for (int k = 0; k < ai.length; k++) {
            if ((i + ai[k]) < size && (j + aj[k]) < size && (i + ai[k]) >= 0 && (j + aj[k]) >= 0) {
                Msbutton checkbutton = board[i][j];
                Msbutton sbutton = board[i + ai[k]][j + aj[k]];
                if (sbutton.mine == -1) {
                    checkbutton.count++;
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        Msbutton b = (Msbutton) view;
        if ((!loosegame) && !(b.open)) {
            if (b.longclick) {
                b.setBackground( getResources().getDrawable( R.drawable.flag ) );
                b.longclick = false;
            } else {
                b.longclick = true;
                b.setBackgroundResource( 0 );
                b.setBackground( getResources().getDrawable( R.drawable.pressed ) );
            }
        }
        return true;
    }

    public void youwin() {
        loosegame = true;
        timeSwapBuff += timeInMilliSecond;
        customHnadler.removeCallbacks( updateTimeThread );
        Toast.makeText( this, "You win"+" "+getIntent().getStringExtra( "name" ), Toast.LENGTH_SHORT ).show();
        String name=getIntent().getStringExtra( "name" );
        //Intent intent=new Intent( this,highScore.class );
        SharedPreferences sharedPreferences=getSharedPreferences( "minesweeper", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String updeatestring=min+":"+sec+":"+milisec;
        editor.putString( UPDATENAME,name );
        editor.putString( UPDATESCORE,updeatestring );
          editor.commit();



    }

}
