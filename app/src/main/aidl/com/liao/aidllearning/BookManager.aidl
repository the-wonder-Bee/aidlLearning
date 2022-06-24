// BookManager.aidl
package com.liao.aidllearning;

import com.liao.aidllearning.Book;

// Declare any non-default types here with import statements

interface BookManager {
    const int i = 0;
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addBook(in Book book);
    void removeBook();
    int getBookNum();
}