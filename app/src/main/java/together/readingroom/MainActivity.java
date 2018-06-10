package together.readingroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_finding, R.id.btn_signup, R.id.btn_manager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_finding:
                Common.isManager = 1;
                startActivity(new Intent(MainActivity.this, Login.class));
                break;
            case R.id.btn_signup:
                startActivity(new Intent(MainActivity.this, SignUp.class));
                break;
            case R.id.btn_manager:
                Common.isManager = 3;
                startActivity(new Intent(MainActivity.this, Login.class));
                break;
        }
    }
}
