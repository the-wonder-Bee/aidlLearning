package com.liao.aidllearning;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BookManagerService extends Service {

    private String TAG = "BookManagerService";

    private List<Book> bookList = new ArrayList<>();

    IBinder bookManager = new BookManager.Stub(){
        @Override
        public void addBook(Book book) throws RemoteException {
            Log.d(TAG, "添加: "+book.toString());
            bookList.add(book);
        }

        @Override
        public void removeBook() throws RemoteException {
            Log.d(TAG, "删除: ");
            bookList.remove(bookList.size()-1);
        }

        @Override
        public int getBookNum() throws RemoteException {
            Log.d(TAG, "书本数量: "+bookList.size());
            return bookList.size();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        bookList.add(new Book("西游记",1,59.0));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }
}
