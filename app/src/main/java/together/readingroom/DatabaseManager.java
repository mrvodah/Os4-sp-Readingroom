package together.readingroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by VietVan on 10/06/2018.
 */

public class DatabaseManager extends SQLiteAssetHelper {

    private static final String TAG = "TAG";
    public static String DB_NAME = "homestayv1.db";
    public static String TABLE_SINHVIEN = "SinhVien";
    public static String TABLE_PHONG = "Phong";
    public static int DB_VERSION = 1;

    public DatabaseManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public List<Student> getList(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_SINHVIEN, null);
        List<Student> list = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new Student(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)

            ));

            cursor.moveToNext();
        }

        Log.d(TAG, "getList: " + list.size());
        return list;

    }

    public List<Phong> getListPhong(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_PHONG, null);
        List<Phong> list = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new Phong(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            ));

            cursor.moveToNext();
        }

        Log.d(TAG, "getList: " + list.size());
        return list;

    }

    public void updatePhong(Phong phong, String timebegin, String timeend, String mID){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tt", 1);
        contentValues.put("timebegin", timebegin);
        contentValues.put("timeend", timeend);
        contentValues.put("studentid", mID);

        db.update(TABLE_PHONG,contentValues,"id=?",new String[]{String.valueOf(phong.getmID())});

    }

    public void capnhatDS(List<Phong> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getTt() == 0){

            }
            else{
                SimpleDateFormat simple = new SimpleDateFormat("MMM dd yyy, HH:mm");
                try {
                    Date date = simple.parse(list.get(i).getTimeend());
                    if(System.currentTimeMillis() > date.getTime()){
                        list.get(i).setTt(0);
                        list.get(i).setTimebegin("");
                        list.get(i).setTimeend("");
                        list.get(i).setMidnum("");
                        suaP(list.get(i));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public int suaP(Phong phong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tt", phong.getTt());
        values.put("timebegin", phong.getTimebegin());
        values.put("timeend", phong.getTimeend());
        values.put("studentid", phong.getMidnum());

        return db.update(TABLE_PHONG,values,"id=?",new String[] { String.valueOf(phong.getmID())});

    }

    public Student getStudentwmIDNum(String mID){
        List<Student> list = getList();
        for(Student u : list)
            if(u.getMidnum().equals(mID))
                return u;

        return null;
    }

    public Student getStudent(String user, String pass){

        List<Student> list = getList();
        for(Student u : list)
            if(u.getmName().equals(user) && u.getmPassword().equals(pass))
                return u;

        return null;

    }

    public void addStudent(String user, String pass, String email, String phone, String stuid){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", user);
        values.put("pass", pass);
        values.put("email", email);
        values.put("phone", phone);
        values.put("studentid", stuid);

        sqLiteDatabase.insert(TABLE_SINHVIEN, null, values);

    }

    public int addStudent(Student student){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", student.getmName());
        values.put("pass", "");
        values.put("email", student.getmEmail());
        values.put("phone", student.getmPhoneNumber());
        values.put("studentid", student.getMidnum());

        return (int) sqLiteDatabase.insert(TABLE_SINHVIEN, null, values);

    }

    public int updateStudent(int id, Student student){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",student.getmName());
        contentValues.put("studentid",student.getMidnum());
        contentValues.put("email",student.getmEmail());
        contentValues.put("phone",student.getmPhoneNumber());

        return db.update(TABLE_SINHVIEN,contentValues,"id=?",new String[]{String.valueOf(id)});

    }

    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SINHVIEN,"id = ?",new String[] {String.valueOf(id)});
    }

}
