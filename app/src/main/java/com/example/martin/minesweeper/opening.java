package com.example.martin.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class opening extends AppCompatActivity implements View.OnClickListener {
Button bt;
EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_opening );
        bt=findViewById( R.id.button );
        e=findViewById( R.id.editText );
    }

    @Override
    public void onClick(View view) {

        int id=view.getId();

        if(id==R.id.button){
       if(!(e.getText().toString().equals( "" ))){
           Intent intent=new Intent( opening.this,MainActivity.class );
           intent.putExtra( "name",e.getText().toString() );
           startActivity( intent );


       }else {
           Toast.makeText( opening.this,"Please enter your name ",Toast.LENGTH_SHORT ).show();
       }}else if(id==R.id.highscore){

         Intent i =new Intent( this,highScore.class );
         startActivity( i );


        }

    }
}
