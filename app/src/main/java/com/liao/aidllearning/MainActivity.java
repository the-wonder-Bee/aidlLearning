package com.liao.aidllearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    TextView textView;

    BookManager bookManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bind = findViewById(R.id.bind_btn);
        Button unbind = findViewById(R.id.unbind_btn);
        Button addbook = findViewById(R.id.add_btn);
        Button delbook = findViewById(R.id.del_btn);
        textView = findViewById(R.id.book_num);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        addbook.setOnClickListener(this);
        delbook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bind_btn){
            Intent intent = new Intent(MainActivity.this, BookManagerService.class);
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }else if(v.getId() == R.id.unbind_btn) {
            unbindService(mServiceConnection);
        }else if(v.getId() == R.id.add_btn) {
            try {
                bookManager.addBook(new Book("西游记",1,59.0));
                textView.setText("当前书本数量："+bookManager.getBookNum());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if(v.getId() == R.id.del_btn) {
            try {
                if (bookManager.getBookNum() != 0){
                    bookManager.removeBook();
                    textView.setText("当前书本数量："+bookManager.getBookNum());
                }
                else{
                    Log.d(TAG, "没有书籍可以删除了");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    //连接service
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = BookManager.Stub.asInterface(service);
            try {
                Log.d(TAG, "已连接到service,当前有: "+bookManager.getBookNum()+"本书");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}