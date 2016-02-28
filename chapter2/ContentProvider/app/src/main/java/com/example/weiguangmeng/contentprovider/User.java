package com.example.weiguangmeng.contentprovider;

/**
 * Created by weiguangmeng on 16/2/28.
 */
public class User {
    public int userId;
    public String userName;
    public int isMale;

    @Override
    public String toString() {
        return "userId is " + userId + "userName is " + userName + "isMale is " + (isMale == 1);
    }
}
