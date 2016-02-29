// IOnNewBookArrivedListener.aidl
package com.aidltest;
import com.aidltest.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
  void onNewBookArrived(in Book newBook);
}
