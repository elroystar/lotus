package com.lotus.lotusapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 洗衣机dto
 */
public class WashingMachine implements Parcelable {

    private Integer id;

    private Integer num;

    private String state;

    private String dryingPrice;

    private String rinsePrice;

    private String cowboyPrice;

    private String sheetsPrice;

    private String standardPrice;

    private String washingLiquidPrice;

    private String softeningPrice;

    private String disinfectionIngPrice;

    private String disinfectionBeforePrice;

    private String washingLiquidState;

    private String disinfectionState;

    private String rinseState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDryingPrice() {
        return dryingPrice;
    }

    public void setDryingPrice(String dryingPrice) {
        this.dryingPrice = dryingPrice;
    }

    public String getRinsePrice() {
        return rinsePrice;
    }

    public void setRinsePrice(String rinsePrice) {
        this.rinsePrice = rinsePrice;
    }

    public String getCowboyPrice() {
        return cowboyPrice;
    }

    public void setCowboyPrice(String cowboyPrice) {
        this.cowboyPrice = cowboyPrice;
    }

    public String getSheetsPrice() {
        return sheetsPrice;
    }

    public void setSheetsPrice(String sheetsPrice) {
        this.sheetsPrice = sheetsPrice;
    }

    public String getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getWashingLiquidPrice() {
        return washingLiquidPrice;
    }

    public void setWashingLiquidPrice(String washingLiquidPrice) {
        this.washingLiquidPrice = washingLiquidPrice;
    }

    public String getSofteningPrice() {
        return softeningPrice;
    }

    public void setSofteningPrice(String softeningPrice) {
        this.softeningPrice = softeningPrice;
    }

    public String getDisinfectionIngPrice() {
        return disinfectionIngPrice;
    }

    public void setDisinfectionIngPrice(String disinfectionIngPrice) {
        this.disinfectionIngPrice = disinfectionIngPrice;
    }

    public String getDisinfectionBeforePrice() {
        return disinfectionBeforePrice;
    }

    public void setDisinfectionBeforePrice(String disinfectionBeforePrice) {
        this.disinfectionBeforePrice = disinfectionBeforePrice;
    }

    public String getWashingLiquidState() {
        return washingLiquidState;
    }

    public void setWashingLiquidState(String washingLiquidState) {
        this.washingLiquidState = washingLiquidState;
    }

    public String getDisinfectionState() {
        return disinfectionState;
    }

    public void setDisinfectionState(String disinfectionState) {
        this.disinfectionState = disinfectionState;
    }

    public String getRinseState() {
        return rinseState;
    }

    public void setRinseState(String rinseState) {
        this.rinseState = rinseState;
    }

    public WashingMachine() {
    }

    public WashingMachine(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            num = null;
        } else {
            num = in.readInt();
        }
        state = in.readString();
        dryingPrice = in.readString();
        rinsePrice = in.readString();
        cowboyPrice = in.readString();
        sheetsPrice = in.readString();
        standardPrice = in.readString();
        washingLiquidPrice = in.readString();
        softeningPrice = in.readString();
        disinfectionIngPrice = in.readString();
        disinfectionBeforePrice = in.readString();
        washingLiquidState = in.readString();
        disinfectionState = in.readString();
        rinseState = in.readString();
    }

    public static final Creator<WashingMachine> CREATOR = new Creator<WashingMachine>() {
        @Override
        public WashingMachine createFromParcel(Parcel in) {
            return new WashingMachine(in);
        }

        @Override
        public WashingMachine[] newArray(int size) {
            return new WashingMachine[size];
        }
    };

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
        if (num == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(num);
        }
        dest.writeString(state);
        dest.writeString(dryingPrice);
        dest.writeString(rinsePrice);
        dest.writeString(cowboyPrice);
        dest.writeString(sheetsPrice);
        dest.writeString(standardPrice);
        dest.writeString(washingLiquidPrice);
        dest.writeString(softeningPrice);
        dest.writeString(disinfectionIngPrice);
        dest.writeString(disinfectionBeforePrice);
        dest.writeString(washingLiquidState);
        dest.writeString(disinfectionState);
        dest.writeString(rinseState);
    }
}
