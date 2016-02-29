package com.serialparcelable;

import java.io.Serializable;

/**
 * Created by weiguangmeng on 16/2/29.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 519067123721295773L;

    public int userId;
    public String userName;
    public boolean isMale;

    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    @Override
    public String toString() {
        return "user id is " + userId + ", user name is " + userName + ", isMale is " + isMale;
    }
}
