package together.readingroom;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {


    @BindView(R.id.tv_lg)
    TextView tvLg;
    @BindView(R.id.edt_lg_Username)
    MaterialEditText edtLgUsername;
    @BindView(R.id.edt_lg_Password)
    MaterialEditText edtLgPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "NABILA.TTF");
        tvLg.setTypeface(typeface);



    }


    @OnClick(R.id.lg_btnSignIn)
    public void onViewClicked() {

        if(edtLgUsername.getText().toString().equals("") ||
                edtLgPassword.getText().toString().equals("")){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else{

            if(Common.isManager == 1){

                Student student = new DatabaseManager(Login.this).getStudent(
                        edtLgUsername.getText().toString(),
                        edtLgPassword.getText().toString()
                );

                if(student != null){

                    Common.student = student;
                    startActivity(
                            new Intent(Login.this, ListRoom.class)
                    );
                    finish();

                }
                else{

                    Toast.makeText(this, "Wrong Usernamme or Password!", Toast.LENGTH_SHORT).show();

                }

            }
            else if(Common.isManager == 3){

                if(edtLgUsername.getText().toString().equals("admin") &&
                        edtLgPassword.getText().toString().equals("admin")){

                    startActivity(new Intent(Login.this, Manager.class));
                    finish();

                }
                else{
                    Toast.makeText(this, "Wrong Usernamme or Password Admin!", Toast.LENGTH_SHORT).show();
                }

            }



        }

    }
}
