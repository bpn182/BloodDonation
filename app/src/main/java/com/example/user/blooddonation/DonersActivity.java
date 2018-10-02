package com.example.user.blooddonation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public class DonersActivity extends AppCompatActivity {

    String RegisterUrl = "https://cosmos182.herokuapp.com/donation/register";
    ActionBar toolbar;
    AQuery aquery;
    EditText name, email, phone, lastDonated, group, address, district;
    Button submit,exit;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doners);

        aquery = new AQuery(this);

        toolbar = getSupportActionBar();
        toolbar.setTitle("Donate");

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        lastDonated = findViewById(R.id.lastDonated);
        group = findViewById(R.id.group);
        district = findViewById(R.id.district);
        address = findViewById(R.id.address);
        submit = findViewById(R.id.submit);
        exit = findViewById(R.id.exit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
                postData();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonersActivity.this,ReceiverActivity.class);
                startActivity(intent);
            }
        });
    }


    public void postData() {

        String nameValue = name.getText().toString();
        String phoneValue = phone.getText().toString();
        String emailValue = email.getText().toString();
        String lastDonatedValue = lastDonated.getText().toString();
        String groupValue = group.getText().toString();
        String districtValue = district.getText().toString();
        String addressValue = address.getText().toString();



        if(isFieldEmpty(name)
                && isValidMobile(phoneValue)
                && isValidEmail(emailValue)
                && isFieldEmpty(lastDonated)
                && isFieldEmpty(group)
                && isFieldEmpty(district)
                && isFieldEmpty(address))
        {

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("name", nameValue);
            param.put("phone", phoneValue);
            param.put("email", emailValue);
            param.put("lastDonated", lastDonatedValue);
            param.put("group", groupValue);
            param.put("district", districtValue);
            param.put("address", addressValue);


            aquery.ajax(RegisterUrl, param, JSONArray.class, new AjaxCallback<JSONArray>() {
                @Override
                public void callback(String url, JSONArray array, AjaxStatus status) {
                    super.callback(url, array, status);

                    Log.i("response", url + "response:" + array);
                    Toast.makeText(DonersActivity.this,"Successfully Inserted",Toast.LENGTH_LONG).show();
                    MakeInputEmpty();
                }
            });

        }

        else{
            Toast.makeText(DonersActivity.this, "Invalid Email or Phone", Toast.LENGTH_SHORT).show();
        }
    }

    public void MakeInputEmpty(){
        name.setText("");
        phone.setText("");
        email.setText("");
        lastDonated.setText("");
        group.setText("");
        district.setText("");
        address.setText("");

    }

    public void validateInput(){

    }

    //validation
    public boolean isFieldEmpty(EditText view){
        String value = view.getText().toString();
        if(value.length()>0){
            return true;

        }else{
            view.setError("Enter value");
            return false;
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;

        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();

    }

}
