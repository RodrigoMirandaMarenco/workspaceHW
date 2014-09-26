package com.rodrigo.venuefinder.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

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

}
