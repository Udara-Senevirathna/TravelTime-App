package com.udara.traveltime;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Query;

public class QueryParcelable implements Parcelable {
    private Query query;

    public QueryParcelable(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }

    protected QueryParcelable(Parcel in) {
        // Read the data from the Parcel and reconstruct the Query object
        // Make sure to read the data in the same order it was written
        // Example: query = FirebaseDatabase.getInstance().getReference().child("Register Routes").orderByChild("Arrival").equalTo(in.readString());
    }

    public static final Creator<QueryParcelable> CREATOR = new Creator<QueryParcelable>() {
        @Override
        public QueryParcelable createFromParcel(Parcel in) {
            return new QueryParcelable(in);
        }

        @Override
        public QueryParcelable[] newArray(int size) {
            return new QueryParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write the data of the Query object to the Parcel
        // Example: dest.writeString(query.getSomeValue());
    }
}
