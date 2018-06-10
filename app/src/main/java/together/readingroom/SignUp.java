package together.readingroom;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity {

    @BindView(R.id.tv_lg)
    TextView tvLg;
    @BindView(R.id.edt_su_Password)
    MaterialEditText edtSuPassword;
    @BindView(R.id.edt_su_Email)
    MaterialEditText edtSuEmail;
    @BindView(R.id.edt_su_Phone)
    MaterialEditText edtSuPhone;
    @BindView(R.id.edt_su_StudentID)
    MaterialEditText edtSuStudentID;
    @BindView(R.id.edt_su_Username)
    MaterialEditText edtSuUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "NABILA.TTF");
        tvLg.setTypeface(typeface);

    }

    @OnClick(R.id.lg_btnSignIn)
    public void onViewClicked() {

        if (edtSuUsername.getText().equals("") ||
                edtSuPassword.getText().equals("") ||
                edtSuEmail.getText().equals("") ||
                edtSuPhone.getText().equals("") ||
                edtSuStudentID.getText().equals("")){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            new DatabaseManager(SignUp.this).addStudent(
                    edtSuUsername.getText().toString(),
                    edtSuPassword.getText().toString(),
                    edtSuEmail.getText().toString(),
                    edtSuPhone.getText().toString(),
                    edtSuStudentID.getText().toString()
            );
            Toast.makeText(this, "Create Account successfull~", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
