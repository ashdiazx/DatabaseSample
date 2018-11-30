package marian.diaz.com.databasesample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eFN, eLN, eGrade;
    dhelper helper;
    Cursor table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new dhelper(this);
        table = helper.populateTable();
        table.moveToFirst();
        eFN = findViewById(R.id.etFirstName);
        eLN = findViewById(R.id.etLastName);
        eGrade = findViewById(R.id.etLabGrade);
        eFN.setText(table.getString(1));
        eLN.setText(table.getString(2));
        eFN.setText(table.getString(3));
    }

    public void addRecord(View v){
        String fname = eFN.getText().toString();
        String lname = eLN.getText().toString();
        int grade = Integer.parseInt(eGrade.getText().toString());
        boolean IsInserted = helper.insert(fname, lname, grade);
        if(IsInserted == true) {
            Toast.makeText(this,"record inserted... ", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"record not inserted... ", Toast.LENGTH_LONG).show();
        }
    }
}
