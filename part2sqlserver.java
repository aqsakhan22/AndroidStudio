package com.example.abc.databasepractice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class part2sqlserver extends AppCompatActivity {
    EditText edittext;
    Button button;
    ProgressBar pbbar;
    TextView textview;
    String DB_URL="jdbc:jtds:sqlserver://192.168.0.108/AndroidDb";
    String USER="sa";
    String PASS="123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2sqlserver);
        edittext=(EditText) findViewById(R.id.edittext);
textview=(TextView) findViewById(R.id.textview);
        button=(Button) findViewById(R.id.button);
        pbbar=(ProgressBar) findViewById(R.id.progressBar);

        pbbar.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send send=new Send();
                send.execute("");
            }
        });

    }

    class Send extends AsyncTask<String,String,String> {
        String z = "";
        Boolean isSuccess = false;
        String userid = edittext.getText().toString();
        protected void onPreExecute() {
            textview.setText("plz wait inserting data");

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
                if(conn==null){
                    z="connection goes to wrong";
                }
                else{

                    String query="insert into part1 values('"+userid+"')";
                    Statement stmt=conn.createStatement();
                    stmt.executeUpdate(query);
                    z="success";
                    conn.close();

                }
            }
            catch (Exception e){
                z="unsucces";
                e.getMessage();
            }
            return z;
        }
        protected void onPostExecute(String s) {


            textview.setText(z);
        }
        }
    }

