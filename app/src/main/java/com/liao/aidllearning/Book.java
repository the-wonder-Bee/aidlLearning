package com.liao.aidllearning;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    String name;
    int id;

    public Book(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static Creator<Book> getCREATOR() {
        return CREATOR;
    }

    double price;

    protected Book(Parcel in) {
        name = in.readString();
        id = in.readInt();
        price = in.readDouble();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeDouble(price);
    }
}
