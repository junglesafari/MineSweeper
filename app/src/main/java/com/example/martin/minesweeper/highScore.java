package com.example.martin.minesweeper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class highScore extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String HIGHSCOREFIRST = "0:0:0";
    private static final String NAMEFIRST = "PC";
    private static final String NAMESECOND = "PC";
    private static final String NAMETHIRD = "PC";
    private static final String HIGHSCORESECOND = "0:0:0";
    private static final String HIGHSCORETHIRD = "0:0:0";
    TextView first;
    TextView second;
    TextView third;

     TextView firstScore;

     TextView secondScore;

    TextView thirdScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_high_score );

        sharedPreferences = getSharedPreferences( "minesweeper", MODE_PRIVATE );
        first = findViewById( R.id.first );
        second = findViewById( R.id.second );
        third = findViewById( R.id.third );
        firstScore = findViewById( R.id.firstScore);
        secondScore = findViewById( R.id.secondScore);
        thirdScore = findViewById( R.id.thirdScore);


        setScore();




    }
    public  void setScore(){
        String n1 = sharedPreferences.getString( "NAMEFIRST", "pc" );
       // String highscorefirst = sharedPreferences.getString( HIGHSCOREFIRST, null );
        String n2 = sharedPreferences.getString( "NAMESECOND", "pc" );
       // String highscoresecond = sharedPreferences.getString( HIGHSCORESECOND, null );
        String n3 = sharedPreferences.getString( "NAMETHIRD", "pc" );
       // String highscorethird = sharedPreferences.getString( HIGHSCORETHIRD, null );
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String p1=sharedPreferences.getString( "HIGHSCOREFIRST","100:0:0" );
        String p2=sharedPreferences.getString( "HIGHSCORESECOND","100:0:0");
        String p3=sharedPreferences.getString( "HIGHSCORETHIRD","100:0:0" );
        String xxx=sharedPreferences.getString( "UPDATESCORE","100:0:0");
      //  String updateScore1=sharedPreferences.getString( "UPDATESCORE",null );
        String player_name=sharedPreferences.getString( "UPDATENAME","pc");
Log.i("namep",player_name+" "+p1+" "+p2+" "+p3+" "+xxx);

        if(p1!=null&&p2!=null&&p3!=null){
            if(highScoreComparer(xxx,p1)){
                p3=p2;n3=n2;
                p2=p1;n2=n1;
                p1=xxx;n1=player_name;
                Log.i("namepq",player_name+" jio"+p1+" "+p2+" "+p3+" "+xxx);
            }else if(highScoreComparer(xxx,p2)){
                p3=p2;n3=n2;
                p2=xxx;n2=player_name;
            }else if(highScoreComparer(xxx,p3)){
                p3=xxx;n3=player_name;
            }

            editor.putString("HIGHSCOREFIRST",p1);
            editor.putString("HIGHSCORESECOND",p2);
            editor.putString("HIGHSCORETHIRD",p3);

            editor.putString("NAMEFIRST",n1);
            editor.putString("NAMESECOND",n2);
            editor.putString("NAMETHIRD",n3);

        }else if(p1!=null&&p2!=null){
            if(highScoreComparer(xxx,p1)){
                p3=p2;p2=p1;p1=xxx;
                n3=n2;n2=n1;n1=player_name;
            }else if(highScoreComparer(xxx,p2)){
                p3=p2;p2=xxx;
                n3=n2;n2=player_name;
            }
            editor.putString("HIGHSCOREFIRST",p1);
            editor.putString("HIGHSCORESECOND",p2);
            editor.putString("HIGHSCORETHIRD",p3);

            editor.putString("NAMEFIRST",n1);
            editor.putString("NAMESECOND",n2);
            editor.putString("NAMETHIRD",n3);

        }else if(p1!=null){
            if(highScoreComparer(xxx,p1)){
                p2=p1;p1=xxx;
                n2=n1;n1=player_name;
            }else{
                p2=xxx;
                n2=player_name;
            }
            editor.putString("HIGHSCOREFIRST",p1);
            editor.putString("HIGHSCORESECOND",p2);

            editor.putString("NAMEFIRST",n1);
            editor.putString("NAMESECOND",n2);
        }else{
            p1=xxx;
            n1=player_name;
            editor.putString("HIGHSCOREFIRST",p1);
            editor.putString("NAMEFIRST",n1);
        }
        editor.apply();


//        int firstScoremili=Integer.parseInt(  firstScore[0])*60*1000+Integer.parseInt(  firstScore[1])*1000+Integer.parseInt(  firstScore[2]);
//        int secondScoremili=Integer.parseInt(  secondScore[0])*60*1000+Integer.parseInt(  secondScore[1])*1000+Integer.parseInt(  secondScore[2]);
//        int thirdScoremili=Integer.parseInt(  thirdScore[0])*60*1000+Integer.parseInt(  thirdScore[1])*1000+Integer.parseInt(  thirdScore[2]);
//        int updateScoremili=Integer.parseInt(  updateScore[0])*60*1000+Integer.parseInt(  updateScore[1])*1000+Integer.parseInt(  updateScore[2]);
//
//        if(firstScoremili==0||updateScoremili<firstScoremili){
//            editor.putString(  "NAMEFIRST",name);
//            editor.putString( "HIGHSCOREFIRST",updateScore1 );
//            editor.commit();
//        }else if(secondScoremili==0||updateScoremili<secondScoremili){
//            editor.putString(  "NAMESECOND",name);
//            editor.putString( "HIGHSCORESECOND",updateScore1 );
//            editor.commit();
//
//        }else if(secondScoremili==0||updateScoremili<thirdScoremili){
//            editor.putString(  "NAMETHIRD",name);
//            editor.putString( "HIGHSCORETHIRD",updateScore1 );
//            editor.commit();
//
//        }

        if (n1 != null&&!p1.equals( "100:0:0" )) {

            first.setText( n1 );
        }
        if (n2 != null&&!p2.equals( "100:0:0" ) ){
            second.setText( n2 );
        }
        if (n3 != null&&!p3.equals( "100:0:0" )) {
            third.setText( n3 );
        }
        if (p1!= null&&!p1.equals( "100:0:0" )) {
            firstScore.setText( p1 );
        }
        if (p2!= null&&!p2.equals( "100:0:0" )) {
            secondScore.setText( p2 );
        }
        if (p3 != null&&!p3.equals( "100:0:0" )) {
            thirdScore.setText( p3 );
        }

   SharedPreferences.Editor et=sharedPreferences.edit();
        et.putString( "UPDATENAME","pc" );
        et.putString("UPDATESCORE","100:0:0");
         et.commit();



    }


    private boolean highScoreComparer(String s, String p) {


        String y[]=s.split(":");
        String z[]=p.split(":");
        int s1=0;
        for(int i=0;i<y.length;i++){
            s1*=1000;
            s1+=Integer.parseInt(y[i]);
        }
        int s2=0;
        for(int i=0;i<y.length;i++){
            s2*=1000;
            s2+=Integer.parseInt(z[i]);
        }
        if(s1>s2)
            return false;
        else
            return true;
    }
}
