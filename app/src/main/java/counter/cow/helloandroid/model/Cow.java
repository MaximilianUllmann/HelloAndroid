package counter.cow.helloandroid.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Cow implements Parcelable{

    private int id;
    private int breed;

    public Cow(int breed, int id){
        this.breed = breed;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBreed() {
        return breed;
    }

    public void setBreed(int breed) {
        this.breed = breed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(breed);
        dest.writeInt(id);
    }

    public static final Parcelable.Creator<Cow> CREATOR = new Parcelable.Creator<Cow>() {
        @Override
        public Cow createFromParcel(Parcel in) {
            return new Cow(in.readInt(), in.readInt());
        }

        @Override
        public Cow[] newArray(int i) {
            return new Cow[i];
        }
    };

}
