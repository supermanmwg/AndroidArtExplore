package com.serialparcelable;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    public final static String serialFile = "cache.txt";
    public final static String PATH = Environment.getExternalStorageDirectory() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFile();
        User user = new User(12, "mwg", true);
        try {
            setSerializable(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.d(TAG, getSerializable().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initFile() {
        File hkappDir = new File(PATH);

        if(!hkappDir.exists()){
            hkappDir.mkdir();
        }

        File file = new File(PATH + serialFile);

        try {
            if (!file.exists()) {
                file.createNewFile();//此处报错！
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSerializable(User user) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PATH + serialFile));
        out.writeObject(user);
        out.close();
    }

    public static User getSerializable() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(PATH + serialFile));
        User user = (User) in.readObject();

        return user;
    }
}
