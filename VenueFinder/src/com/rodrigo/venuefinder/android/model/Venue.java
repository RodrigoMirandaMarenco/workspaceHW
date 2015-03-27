package com.rodrigo.venuefinder.android.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue implements Parcelable{
 
	@Expose
	private String zip;
	@Expose
	private String phone;
	@SerializedName("ticket_link")
	@Expose
	private String ticketLink;
	@Expose
	private String state;
	@Expose
	private Integer pcode;
	@Expose
	private String city;
	@Expose
	private Integer id;
	@Expose
	private String tollfreephone;
	@Expose
	private List<Schedule> schedule = new ArrayList<Schedule>();
	@Expose
	private String address;
	@SerializedName("image_url")
	@Expose
	private String imageUrl;
	@Expose
	private String description;
	@Expose
	private String name;
	@Expose
	private Double longitude;
	@Expose
	private Double latitude;

	public String getZip() {
	return zip;
	}

	public void setZip(String zip) {
	this.zip = zip;
	}

	public String getPhone() {
	return phone;
	}

	public void setPhone(String phone) {
	this.phone = phone;
	}

	public String getTicketLink() {
	return ticketLink;
	}

	public void setTicketLink(String ticketLink) {
	this.ticketLink = ticketLink;
	}

	public String getState() {
	return state;
	}

	public void setState(String state) {
	this.state = state;
	}

	public Integer getPcode() {
	return pcode;
	}

	public void setPcode(Integer pcode) {
	this.pcode = pcode;
	}

	public String getCity() {
	return city;
	}

	public void setCity(String city) {
	this.city = city;
	}

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public String getTollfreephone() {
	return tollfreephone;
	}

	public void setTollfreephone(String tollfreephone) {
	this.tollfreephone = tollfreephone;
	}

	public List<Schedule> getSchedule() {
	return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
	this.schedule = schedule;
	}

	public String getAddress() {
	return address;
	}

	public void setAddress(String address) {
	this.address = address;
	}

	public String getImageUrl() {
	return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public Double getLongitude() {
	return longitude;
	}

	public void setLongitude(Double longitude) {
	this.longitude = longitude;
	}

	public Double getLatitude() {
	return latitude;
	}

	public void setLatitude(Double latitude) {
	this.latitude = latitude;
	}
	
	public Venue(){
	}
	
	public Venue(Parcel in) { readFromParcel(in); }
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(phone); 
		dest.writeString(ticketLink);		
		dest.writeString(zip);
		dest.writeString(state);
		dest.writeInt(pcode);
		dest.writeString(city);
		dest.writeInt(id);
		dest.writeString(tollfreephone);
		dest.writeTypedList(schedule);
		dest.writeString(address);
		dest.writeString(imageUrl);
		dest.writeString(description);
		dest.writeString(name);
		dest.writeDouble(longitude);
		dest.writeDouble(latitude);
	}
	
	private void readFromParcel(Parcel in) {
		phone = in.readString();
		ticketLink = in.readString();
		zip = in.readString();
		state = in.readString();
		pcode = in.readInt();
		city = in.readString();
		id = in.readInt();
		tollfreephone = in.readString();
		in.readTypedList(schedule, Schedule.CREATOR) ;
		address = in.readString();
		imageUrl = in.readString();
		description = in.readString();
		name = in.readString();
		longitude = in.readDouble();
		latitude = in.readDouble();
		
	}
	
	public static final Parcelable.Creator<Venue> CREATOR = new Parcelable.Creator<Venue>() { 
		public Venue createFromParcel(Parcel in) { 
			return new Venue(in); 
		}   
		public Venue[] newArray(int size) { 
			return new Venue[size]; 
		} 
	};

	
}