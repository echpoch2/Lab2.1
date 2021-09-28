package tns.fit.bstu.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String[] activitiesField = { "Сидячий образ жизни", "Умеренная активность", "Средняя активность", "Активные люди", "Спортсмены"};
    TextView resultField;
    EditText ageField;
    RadioGroup sexRadioButons;
    EditText weightField;
    EditText tollField;
    Spinner spinner;
    Button button;
    CheckBox checkBox;
    String sex="";
    boolean changes = false;
    boolean ch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        resultField = (TextView) findViewById(R.id.resultField);
        ageField = (EditText) findViewById(R.id.ageField);
        sexRadioButons = (RadioGroup) findViewById(R.id.sexRadioButtons);
        weightField = (EditText) findViewById((R.id.weightField));
        tollField = (EditText) findViewById(R.id.tollField);
        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activitiesField);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                //spinner.setText(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        button.setOnClickListener(this);

    }






    @Override
    public void onClick(View view) {
    setInfo();

    }

    public void setInfo(){
        resultField.setText("")   ;
        String age = ageField.getText().toString();
        String weight = weightField.getText().toString();
        String toll = tollField.getText().toString();
        String spinnerf = spinner.getSelectedItem().toString();
        float spinValue = 0;

        if(spinnerf == "Сидячий образ жизни"){
            spinValue = 1.2f;
        }
        else if(spinnerf == "Умеренная активность"){
            spinValue = 1.375f;
        }
        else if(spinnerf == "Средняя активность"){
            spinValue = 1.55f;
        }
        else if(spinnerf == "Активные люди"){
            spinValue = 1.725f;
        }
        else spinValue = 1.9f;


        if(age.matches("") || weight.matches("") || toll.matches("")|| ch == false ) {
            resultField.setText("Some field or fields is(are) empty!");
        }
        else if(sex == "1") {
            float res = (66.4730f + (13.7516f * Float.parseFloat(weight)) + (5.0033f * Float.parseFloat(toll)) - (Float.parseFloat(age)*6.7550f ))* (spinValue);
            resultField.setText(String.valueOf(res));
        }
        else if(sex == "2") {
            float res2 = (655.0955f + (9.5634f *  Float.parseFloat(weight)) + (1.8496f *  Float.parseFloat(toll)) - (Float.parseFloat(age)* 4.6756f )) * (spinValue);
            resultField.setText(String.valueOf(res2));
        }
    }
    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();

        // Получаем нажатый переключатель
        switch (view.getId()) {
            case R.id.man:
                if (checked) {
                    sex = "1";
                    ch =true;
                }
                break;
            case R.id.woman:
                if (checked) {
                    sex = "2";
                    ch = true;
                }
                break;
        }
    }


    public void onCheckboxClicked(View view) {
        // Получаем флажок
        CheckBox checkBox = (CheckBox) view;
        TextView selection = (TextView) findViewById(R.id.checkBox);
        // Получаем, отмечен ли данный флажок
        if(checkBox.isChecked()) {
            button.setEnabled(true);

        }
        else {
            button.setEnabled(false);
        }
    }

}