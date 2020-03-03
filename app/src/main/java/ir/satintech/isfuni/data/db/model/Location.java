package ir.satintech.isfuni.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;




@Table(name = "Location")
public class Location extends Model implements Parcelable {



    @SerializedName("lat")
    @Expose
    @Column(name = "lat")
    private double lat;


    @SerializedName("log")
    @Expose
    @Column(name = "log")
    private double log;


    @SerializedName("name")
    @Expose
    @Column(name = "name")
    private String name;


    @SerializedName("description")
    @Expose
    @Column(name = "description")
    private String description;


    @SerializedName("phon")
    @Expose
    @Column(name = "phon")
    private String phon;

    @SerializedName("site")
    @Expose
    @Column(name = "site")
    private String site;


    @SerializedName("hoursOfWork")
    @Expose
    @Column(name = "hoursOfWork")
    private String hours_of_work;


    @SerializedName("imageUrl")
    @Expose
    @Column(name = "imageUrl")
    private String image_url;


    @SerializedName("category")
    @Expose
    @Column(name = "category")
    public Category category;


    public void setAaId(Long id) {
        try {
            Field idField = Model.class.getDeclaredField("mId");
            idField.setAccessible(true);
            idField.set(this, id);
        } catch (Exception e) {
            throw new RuntimeException("Reflection failed to get the Active Android ID", e);
        }
    }

    public Location() {
        super();
    }

    public Location( double lat, double log, String name, String description, String phon, String site, String hours_of_work, String image_url, Category category) {
        super();

        this.lat = lat;
        this.log = log;
        this.name = name;
        this.description = description;
        this.phon = phon;
        this.site = site;
        this.hours_of_work = hours_of_work;
        this.image_url = image_url;
        this.category = category;
    }



    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhon() {
        return phon;
    }

    public void setPhon(String phon) {
        this.phon = phon;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getHours_of_work() {
        return hours_of_work;
    }

    public void setHours_of_work(String hours_of_work) {
        this.hours_of_work = hours_of_work;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeDouble(this.lat);
        dest.writeDouble(this.log);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.phon);
        dest.writeString(this.site);
        dest.writeString(this.hours_of_work);
        dest.writeString(this.image_url);
        dest.writeParcelable(this.category, flags);
        dest.writeLong(getId());
    }

    protected Location(Parcel in) {

        this.lat = in.readDouble();
        this.log = in.readDouble();
        this.name = in.readString();
        this.description = in.readString();
        this.phon = in.readString();
        this.site = in.readString();
        this.hours_of_work = in.readString();
        this.image_url = in.readString();
        this.category = in.readParcelable(Category.class.getClassLoader());
        setAaId(in.readLong());
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
