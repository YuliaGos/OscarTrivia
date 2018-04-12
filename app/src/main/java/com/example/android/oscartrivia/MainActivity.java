package com.example.android.oscartrivia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int Score = 0;
    Spinner sp;
    String[] names = {"Katharine Hepburn", "Bette Davis", "Jack Nicholson", "Meryl Streep"};
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        Spinner sp = (Spinner)findViewById(R.id.spinner);
        sp.setOnItemSelectedListener (this);

        ArrayAdapter adapter = new ArrayAdapter (this,android.R.layout.simple_spinner_dropdown_item,names);
        adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter (adapter);

        RadioGroup radioGroupYear = (RadioGroup) findViewById (R.id.radioGroupYear);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(position == 0) {
            Score +=0;
        }
        if(position == 1) {
            Score +=0;
        }
        if(position == 2) {
            Score +=0;
        }
        if(position == 3) {
            Score +=1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void submitQuiz (View view) {
        int Score = findScore ();
        LayoutInflater myInflator=getLayoutInflater();
        View myLayout=myInflator.inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.toastlayout) );
        Toast myToast=new Toast(getApplicationContext());
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(myLayout);
        myToast.show();
        TextView myMessage=(TextView)myLayout.findViewById(R.id.txtvdisplay);

        if (Score == 0) {
            //show a message for a score of zero
            myMessage.setText ("So sorry! Your score is: " + Score);


        } else if (Score >= 1 && Score <= 3) {
            //show a message for a score >= 1 and <= 3
            myMessage.setText ("Just keep trying! Your score is: " + Score);

        } else if (Score >= 4 && Score <= 6) {
            //show a message for a score of greater than or equal to 5 and less than or equal to 7
            myMessage.setText ("Nice try! Your score is: " + Score);

        } else {
            //show a message for a score of 7
            myMessage.setText ("Excellent!!! Your score is " + Score + " out of 7");
        }
    }

    public int findScore() {

        RadioGroup radioGroupYear = (RadioGroup) findViewById (R.id.radioGroupYear);
        RadioGroup radioGroupWeight = (RadioGroup) findViewById (R.id.radioGroupWeight);
        CheckBox checkBoxCategorie1 = (CheckBox) findViewById (R.id.checkbox_categorie1);
        CheckBox checkBoxCategorie2 = (CheckBox) findViewById (R.id.checkbox_categorie2);
        CheckBox checkBoxCategorie3 = (CheckBox) findViewById (R.id.checkbox_categorie3);
        CheckBox checkBoxCategorie4 = (CheckBox) findViewById (R.id.checkbox_categorie4);
        CheckBox checkBoxCategorie5 = (CheckBox) findViewById (R.id.checkbox_categorie5);
        CheckBox checkBoxCategorie6 = (CheckBox) findViewById (R.id.checkbox_categorie6);
        CheckBox checkBoxWinner2 = (CheckBox) findViewById (R.id.checkbox_winner2);
        RadioGroup radioGroup = (RadioGroup) findViewById (R.id.radioGroup);
        EditText editTextHost=(EditText) findViewById (R.id.edittext_host);
        String hostName=editTextHost.getText().toString ();


        //Question1 (Year)
        if (radioGroupYear.getCheckedRadioButtonId () == R.id.radio_year4) {
            Score += 1;
        }
        //Question2 (Weight)
        if (radioGroupWeight.getCheckedRadioButtonId () == R.id.radio_weight3) {
            Score += 1;
        }
        //Question3 (Categories)
        if (!checkBoxCategorie1.isChecked () && checkBoxCategorie2.isChecked () && checkBoxCategorie3.isChecked () && !checkBoxCategorie4.isChecked () && !checkBoxCategorie5.isChecked () && checkBoxCategorie6.isChecked ()) {
            Score += 1;
        } else {
            Score += 0;
        }
        //Question4 (Host)
        if (hostName.equals("Jimmy Kimmel ") || hostName.equals("jimmy kimmel ") || hostName.equals("Jimmy Kimmel") || hostName.equals("jimmy kimmel ") ){
            Score += 1;
        }
        //Question 5

        if (checkBoxWinner2.isChecked ()) {
            Score += 1;
        } else {
            Score += 0;
        }
        //Question7
        if (radioGroup.getCheckedRadioButtonId () == R.id.radio_true) {
            Score += 1;
        }
        if (position == 0){
            Score +=0;
        }
        if (position == 1){
            Score +=0;
            if (position == 2){
                Score +=0;
            }
        }
        if (position == 3){
            Score +=1;
        }
        return Score;

    }

    public void shareScore (View view){
        EditText editTextUserName=(EditText) findViewById (R.id.userName);
        String userName=editTextUserName.getText().toString();
        int Score=findScore ();
        String message="";
        if (Score > 0){
            message= "Congratulations!\n Your friend " + userName + " got a score of " + Score + " out of 7 in Oscar Trivia Quiz!\n Download the app from the playstore and try it now!!!";
        } else {
            message="Your friend " + userName + " got a score of " + Score + " in Oscar Trivia Quiz!\n App is availabe in playstore!";
        }
        Intent intent = new Intent (Intent.ACTION_SENDTO);
        intent.setData (Uri.parse ("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, userName + " got a score of " + Score + " in Oscar Trivia Quiz App!");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity (intent);
        }
    }

    public void resetQuiz (View view) {
        Score=0;
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroupYear);
        radioGroup1.clearCheck();
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroupWeight);
        radioGroup2.clearCheck();
        RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup3.clearCheck();
        EditText userName=(EditText) findViewById (R.id.userName);
        userName.getText().clear();
        EditText hostName=(EditText) findViewById (R.id.edittext_host);
        hostName.getText().clear();
        CheckBox checkBoxCategorie1 = (CheckBox) findViewById(R.id.checkbox_categorie1);
        checkBoxCategorie1.setChecked(false);
        CheckBox checkBoxCategorie2 = (CheckBox) findViewById(R.id.checkbox_categorie2);
        checkBoxCategorie2.setChecked(false);
        CheckBox checkBoxCategorie3 = (CheckBox) findViewById(R.id.checkbox_categorie3);
        checkBoxCategorie3.setChecked(false);
        CheckBox checkBoxCategorie4 = (CheckBox) findViewById (R.id.checkbox_categorie4);
        checkBoxCategorie4.setChecked(false);
        CheckBox checkBoxCategorie5 = (CheckBox) findViewById (R.id.checkbox_categorie5);
        checkBoxCategorie5.setChecked(false);
        CheckBox checkBoxCategorie6 = (CheckBox) findViewById (R.id.checkbox_categorie6);
        checkBoxCategorie6.setChecked(false);
        CheckBox checkBoxWinner1 = (CheckBox) findViewById (R.id.checkbox_winner1);
        checkBoxWinner1.setChecked(false);
        CheckBox checkBoxWinner2 = (CheckBox) findViewById(R.id.checkbox_winner2);
        checkBoxWinner2.setChecked(false);
        CheckBox checkBoxWinner3 = (CheckBox) findViewById(R.id.checkbox_winner3);
        checkBoxWinner3.setChecked(false);
        CheckBox checkBoxWinner4 = (CheckBox) findViewById(R.id.checkbox_winner4);
        checkBoxWinner4.setChecked(false);
        LayoutInflater myInflator=getLayoutInflater();
        View myLayout=myInflator.inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.toastlayout) );
        Toast myToast=new Toast(getApplicationContext());
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(myLayout);
        myToast.show();
        TextView myMessage=(TextView)myLayout.findViewById(R.id.txtvdisplay);;
        myMessage.setText ("Quiz was reset.Try again!");
        sp = (Spinner) findViewById(R.id.spinner);
        sp.setSelection(0);
    }

}
