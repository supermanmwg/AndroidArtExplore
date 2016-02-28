package com.example.weiguangmeng.contentprovider;

/**
 * Created by weiguangmeng on 16/2/28.
 */
public class Book {
    public int bookId;
    public String bookName;

    @Override
    public String toString() {
        return "Book id is " + bookId + ",Book name is " + bookName;
    }
}
