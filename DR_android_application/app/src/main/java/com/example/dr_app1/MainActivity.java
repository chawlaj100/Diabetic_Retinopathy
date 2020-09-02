package com.example.dr_app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView m_title = (TextView) findViewById(R.id.title);
        TextView m_content1 = (TextView) findViewById(R.id.content1);
        TextView m_content2 = (TextView) findViewById(R.id.content2);

        String string_content1 = "Diabetic Retinopathy is a diabetic complication that causes " +
                "damage to blood vessels in the retina due to the increase in sugar levels in the body. " +
                "Initially the disease does not cause much harm to the eye but it eventually results in impaired vision " +
                "with partial blindness that is then not curable. " +
                "So early detection of this disease is very important to cure the patient effectively. " +
                "Fundus imaging techniques are used by Ophthalmologists to view the lesions/effected-regions in the retina. ";
        m_content1.setText(string_content1);
        String string_content2 = "This application would help you to test your retinopathy level by uploading your retinal image and " +
                "further guide you with " +
                "the next steps to be taken. ";
        m_content2.setText(string_content2);

        button = (Button) findViewById(R.id.btnTest);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity2();
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent (this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}