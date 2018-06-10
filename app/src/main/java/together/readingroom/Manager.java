package together.readingroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class Manager extends AppCompatActivity {

    private EditText edtName;
    private EditText editIdNum;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private EditText edtId;
    private Button btnSave;
    private Button btnUpdate;
    private ListView lvStudent;
    private DatabaseManager dbManager;
    private CustomAdapter customAdapter;
    private List<Student> studentList;
    Student crstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        dbManager = new DatabaseManager(this);
        initWidget();
        studentList = dbManager.getList();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = createStudent();
                int result = dbManager.addStudent(student);
                if(result>0){
                    updateListStudent();
                }
                updateListStudent();
                setAdapter();
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                crstudent = studentList.get(position);
                edtId.setText(String.valueOf(crstudent.getmID()));
                edtName.setText(crstudent.getmName());
                editIdNum.setText(crstudent.getMidnum());
                edtEmail.setText(crstudent.getmEmail());
                edtPhoneNumber.setText(crstudent.getmPhoneNumber());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setmID(Integer.parseInt(String.valueOf(edtId.getText())));
                student.setmName(edtName.getText()+"");
                student.setMidnum(editIdNum.getText()+"");
                student.setmEmail(edtEmail.getText()+"");
                student.setmPhoneNumber(edtPhoneNumber.getText()+"");
                int result = dbManager.updateStudent(crstudent.getmID(), student);
                if(result>0){
                    updateListStudent();
                }
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
            }
        });
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = studentList.get(position);
                int result = dbManager.deleteStudent(student.getmID());
                if(result>0){
                    Toast.makeText(Manager.this, "Delete successfuly", Toast.LENGTH_SHORT).show();
                    updateListStudent();
                }else{
                    Toast.makeText(Manager.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private Student createStudent() {
        String name = edtName.getText().toString();
        String idnum = editIdNum.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();
        String email = edtEmail.getText().toString();

        Student student = new Student(name, email, phoneNumber, idnum);
        return student;
    }

    private void initWidget() {
        edtName = (EditText) findViewById(R.id.edt_name);
        editIdNum = (EditText) findViewById(R.id.edt_idnum);
        edtPhoneNumber = (EditText) findViewById(R.id.edt_number);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnSave = (Button) findViewById(R.id.btn_save);
        lvStudent = (ListView) findViewById(R.id.lv_student);
        edtId = (EditText) findViewById(R.id.edt_id);
        btnUpdate = (Button) findViewById(R.id.btn_update);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_list_student, studentList);
            lvStudent.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount()-1);
        }
    }
    public void updateListStudent(){
        studentList.clear();
        studentList.addAll(dbManager.getList());
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }

}