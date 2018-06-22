package com.example.martin.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        Intent intent=new Intent( opening.this,MainActivity.class );
        intent.putExtra( "name",e.getText().toString() );
        startActivity( intent );
    }
}
