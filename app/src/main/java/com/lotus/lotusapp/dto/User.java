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

    private Integer washing_num;

    private Integer washing_total;

    private Integer reward_num;

    private Integer reward_total;

    private Date create_time;

    private Date update_time;

    public User() {

    }

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        phone = in.readString();
        if (in.readByte() == 0) {
            washing_num = null;
        } else {
            washing_num = in.readInt();
        }
        if (in.readByte() == 0) {
            washing_total = null;
        } else {
            washing_total = in.readInt();
        }
        if (in.readByte() == 0) {
            reward_num = null;
        } else {
            reward_num = in.readInt();
        }
        if (in.readByte() == 0) {
            reward_total = null;
        } else {
            reward_total = in.readInt();
        }
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

    public Integer getWashing_num() {
        return washing_num;
    }

    public void setWashing_num(Integer washing_num) {
        this.washing_num = washing_num;
    }

    public Integer getWashing_total() {
        return washing_total;
    }

    public void setWashing_total(Integer washing_total) {
        this.washing_total = washing_total;
    }

    public Integer getReward_num() {
        return reward_num;
    }

    public void setReward_num(Integer reward_num) {
        this.reward_num = reward_num;
    }

    public Integer getReward_total() {
        return reward_total;
    }

    public void setReward_total(Integer reward_total) {
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
        if (washing_num == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(washing_num);
        }
        if (washing_total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(washing_total);
        }
        if (reward_num == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(reward_num);
        }
        if (reward_total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(reward_total);
        }
    }
}
