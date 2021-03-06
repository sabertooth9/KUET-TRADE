package com.sabertooth.kuettrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.sabertooth.kuettrade.store_and_user_nav_settings.user_x;

public class SettingsActivity extends AppCompatActivity {
    ImageView coverPic;
    CircleImageView proPic;
    EditText etname;
    TextView tvname;
    TextView email,address,phone1,phone2;
    MaterialButton pass,update;
    FloatingActionButton fab;
    DatabaseReference UserRef;
    private void toaster(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        proPic=findViewById(R.id.image_view_settings_profile_pic);
        coverPic=findViewById(R.id.image_view_settings_cover_pic);
        etname=findViewById(R.id.edit_text_setting_user_name);
        tvname = findViewById(R.id.text_view_setting_user_name);
        email=findViewById(R.id.text_view_setting_user_mail);
        address=findViewById(R.id.text_view_setting_user_address);
        phone1=findViewById(R.id.text_view_setting_user_phone_1);
        phone2=findViewById(R.id.text_view_setting_user_phone_2);
        pass=findViewById(R.id.button_setting_show_password);
        fab=findViewById(R.id.fab_setting);
        update = findViewById(R.id.button_update_settings);
        try{
            Picasso.get().load(user_x.proPicUrl).into(proPic);
        }
        catch (Exception e){
            toaster(e.getMessage());
        }try{
            Picasso.get().load(user_x.coverPicUrl).into(coverPic);
        }
        catch (Exception e){
            toaster(e.getMessage());
        }
        UserRef = FirebaseDatabase.getInstance().getReference().child("users").child(user_x.uid);
        tvname.setText(user_x.Name);
        etname.setText(user_x.Name);
        email.setText(user_x.Email);
        address.setText(user_x.Address);
        phone2.setText(user_x.Phone2);
        phone1.setText(user_x.Phone1);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.setText(user_x.password);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setVisibility(View.VISIBLE);
                tvname.setVisibility(View.GONE);
                etname.setVisibility(View.VISIBLE);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname = etname.getText().toString();
                if(newname == null || newname.length()==0){
                    Toast.makeText(getApplicationContext(), "Name Can't Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserRef.child("Name").setValue(newname);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), store_and_user_nav_settings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
