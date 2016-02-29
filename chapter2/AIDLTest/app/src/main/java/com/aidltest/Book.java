package com.aidltest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwg on 16-2-29.
 */
public class Book implements Parcelable {
    public int bookId;
    public String bookName;
    public User user;

    public Book(int bookId, String bookName, User user) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
        dest.writeParcelable(user, 0);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    private Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
        user = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public String toString() {
        return "book id is " + bookId + ", book name is " + bookName + ", " + user.toString();
    }
}
