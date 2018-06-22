package com.example.martin.minesweeper;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;
public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
public int size=10;
public static boolean loosegame=false;
 public  static   int[] ai={-1,-1,-1,0,1,1,1,0};
  public  static int[] aj ={-1,0,1,1,1,0,-1,-1};
LinearLayout rootLayout;
public static int reqcount;
 public Msbutton[][] board;
Random rand;
public static int count;
ArrayList<LinearLayout> rows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      rootLayout=findViewById(R.id.rootLayout);
      setBoard();
      setMine(size);
      for(int i=0;i<size;i++) {
          for(int j=0;j<size;j++){
          checksourrounding( i, j);}
      }
    }
    public void setBoard(){
        board=new Msbutton[size][size];
        rows=new ArrayList<>();
        count=0;
        reqcount=size*size-size;
        rootLayout.removeAllViews();
       for(int i=0;i<size;i++){
        LinearLayout linearLayout=new LinearLayout(this);
        LinearLayout.LayoutParams linearLayoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
        linearLayout.setLayoutParams(linearLayoutParams);
        rootLayout.addView(linearLayout);
        rows.add(linearLayout);
        for (int j=0;j<size;j++){
            Msbutton button=new Msbutton(this);
            LinearLayout.LayoutParams buttonParams=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
            buttonParams.setMargins( 2,2,2,2 );
            button.setLayoutParams(buttonParams);
            button.setBackground( getResources().getDrawable( R.drawable.pressed) );
            button.setOnClickListener(MainActivity.this);
            button.setOnLongClickListener( MainActivity.this );
            LinearLayout row=rows.get(i);
            button.indexi=i;
            button.indexj=j;
            row.addView(button);
            board[i][j]=button;
           }
       }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.reset){
            loosegame=false;
            setBoard();
           setMine(size);
            for(int i=0;i<size;i++) {
                for(int j=0;j<size;j++){
                    checksourrounding( i, j);}
            }
        }else if(id==R.id.four){
            size=4;
            loosegame=false;
            setBoard();
            setMine(4);
            for(int i=0;i<size;i++) {
                for(int j=0;j<size;j++){
                    checksourrounding( i, j);}
            }
        }else if(id==R.id.five){
            size=5;
            setBoard();
            loosegame=false;
            setMine(5);
            for(int i=0;i<size;i++) {
                for(int j=0;j<size;j++){
                    checksourrounding( i, j);}
            }
        }else if(id==R.id.six){
            size=6;
            setBoard();
            loosegame=false;
            setMine(6);
            for(int i=0;i<size;i++) {
                for(int j=0;j<size;j++){
                    checksourrounding( i, j);}
            }
        }


        return super.onOptionsItemSelected(item);

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


            if(reqcount==count){youwin();
       }
        }
    }
    public void setMine(int nummine){
       if(nummine>0){ rand=new Random();
        int i=rand.nextInt(size);
        int j=rand.nextInt(size);
        Msbutton b=board[i][j];
        if(b.mine==-1){
            setMine(nummine);
            return;
        }
        b.mine=-1;
       nummine--;
       setMine(nummine);}
        else {return;}


    }
    @SuppressLint("SetTextI18n")
    public void checksourrounding(int i, int j){
        for(int k=0;k<ai.length;k++){
            if((i+ai[k])<size&&(j+aj[k])<size&&(i+ai[k])>=0&&(j+aj[k])>=0){
            Msbutton checkbutton=board[i][j];
            Msbutton sbutton=board[i+ai[k]][j+aj[k]];
            if(sbutton.mine==-1){
                checkbutton.count++;
            } } } }
    @Override
    public boolean onLongClick(View view) {
        Msbutton b=(Msbutton)view;
      if((!loosegame)&&!(b.open)){
       if(b.longclick){ b.setBackground( getResources().getDrawable( R.drawable.flag ) );
       b.longclick=false;}else {
           b.longclick=true;
          b.setBackgroundResource(0);
           b.setBackground( getResources().getDrawable( R.drawable.pressed) );
       }}
        return true;
    }

    public void youwin() {
        loosegame=true;
        Toast.makeText( this,"You win",Toast.LENGTH_SHORT ).show();
    }
}
