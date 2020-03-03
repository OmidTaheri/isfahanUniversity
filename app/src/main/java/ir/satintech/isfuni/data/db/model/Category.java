package ir.satintech.isfuni.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;


@Table(name = "Category")
public class Category extends Model implements Parcelable {



    @SerializedName("name")
    @Expose
    @Column(name = "name")
    private String name;


    public void setAaId(Long id) {
        try {
            Field idField = Model.class.getDeclaredField("mId");
            idField.setAccessible(true);
            idField.set(this, id);
        } catch (Exception e) {
            throw new RuntimeException("Reflection failed to get the Active Android ID", e);
        }
    }

    public Category() {
        super();
    }

    public Category(int local_id, String name) {
        super();
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(getId());
    }

    protected Category(Parcel in) {
        this.name = in.readString();
        setAaId(in.readLong());
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
