package com.lotus.lotusapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * user表dto
 */
public class User implements Parcelable {

    private Integer id;

    private String phone;

    private String washing_num;

    private String washing_total;

    private String reward_num;

    private String reward_total;

    private Date create_time;

    private Date update_time;

    public User() {

    }

    public User(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        phone = in.readString();
        washing_num = in.readString();
        washing_total = in.readString();
        reward_num = in.readString();
        reward_total = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWashing_num() {
        return washing_num;
    }

    public void setWashing_num(String washing_num) {
        this.washing_num = washing_num;
    }

    public String getWashing_total() {
        return washing_total;
    }

    public void setWashing_total(String washing_total) {
        this.washing_total = washing_total;
    }

    public String getReward_num() {
        return reward_num;
    }

    public void setReward_num(String reward_num) {
        this.reward_num = reward_num;
    }

    public String getReward_total() {
        return reward_total;
    }

    public void setReward_total(String reward_total) {
        this.reward_total = reward_total;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(phone);
        dest.writeString(washing_num);
        dest.writeString(washing_total);
        dest.writeString(reward_num);
        dest.writeString(reward_total);
    }
}
