package com.rodrigo.venuefinder.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule implements Parcelable {

@SerializedName("end_date")
@Expose
private String endDate;
@SerializedName("start_date")
@Expose
private String startDate;

public String getEndDate() {
return endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

public String getStartDate() {
return startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public Schedule(){
}

public Schedule(Parcel in) { readFromParcel(in); }

@Override
public int describeContents() {
	return 0;
}

@Override
public void writeToParcel(Parcel dest, int flags) {
	dest.writeString(endDate); 
	dest.writeString(startDate);		
}

private void readFromParcel(Parcel in) {
	endDate = in.readString(); 
	startDate = in.readString(); 
}

public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() { 
	public Schedule createFromParcel(Parcel in) { 
		return new Schedule(in); 
	}   
	public Schedule[] newArray(int size) { 
		return new Schedule[size]; 
	} 
};

}
