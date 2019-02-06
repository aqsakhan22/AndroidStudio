package com.example.abc.databasepractice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Part1 extends AppCompatActivity {
EditText edittext;
TextView textview,textview1;
Button button;
    String DB_URL="jdbc:mysql://192.168.0.108/androiddb";
    String USER="zzz";
    String PASS="123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
edittext=(EditText) findViewById(R.id.edittext);
textview=(TextView) findViewById(R.id.textview);
button=(Button) findViewById(R.id.button);


button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Send send=new Send();
        send.execute("");
    }
});
    }


    class Send extends AsyncTask<String,String,String>{
    String msg="";

        String text=edittext.getText().toString();

        @Override
        protected void onPreExecute() {
            textview.setText("plz wait inserting data");

        }

        @Override
        protected String doInBackground(String... strings) {


            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
                if(conn==null){
                    msg="connection goes to wrong";
                }
                else{

                    String query="insert into part1 (user) values('"+text+"')";
                    Statement stmt=conn.createStatement();
                    stmt.executeUpdate(query);
                    msg="success";
                    conn.close();

                }
            }
            catch (Exception e){
                msg="unsucces";
e.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            textview.setText(msg);
        }
    }
}
