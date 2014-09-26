package com.rodrigo.venuefinder.android.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {
 
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
	
	
	//ORIGINAL:
	
	
//	// Core fields
//	private long mId;
//	private int mPcode;
//	private int mLatitude;
//	private int mLongitude;
//	private String mName;
//	private String mAddress;
//	private String mCity;
//	private String mState;
//	private String mZip;
//	private String mPhone;
// 
//	// Super Bowl venue fields
//	private String mTollFreePhone;
//	private String mUrl;
//	private String mDescription;
//	private String mTicketLink;
//	private String mImageUrl;
//	private List<ScheduleItem> mSchedule;
// 
//	// computed fields
//	private float mDistance;
// 
//	public String getDescription() {
//		return mDescription;
//	}
// 
//	public void setDescription(String description) {
//		mDescription = description;
//	}
// 
//	public String getTicketLink() {
//		return mTicketLink;
//	}
// 
//	public void setTicketLink(String ticketLink) {
//		mTicketLink = ticketLink;
//	}
// 
//	public List<ScheduleItem> getSchedule() {
//		return mSchedule;
//	}
// 
//	public void setSchedule(List<ScheduleItem> schedule) {
//		mSchedule = schedule;
//	}
// 
//	public String getTollFreePhone() {
//		return mTollFreePhone;
//	}
// 
//	public void setTollFreePhone(String tollFreePhone) {
//		mTollFreePhone = tollFreePhone;
//	}
// 
//	public String getUrl() {
//		return mUrl;
//	}
// 
//	public void setUrl(String url) {
//		mUrl = url;
//	}
// 
//	public Venue() {
// 
//	}
// 
//	public long getId() {
//		return mId;
//	}
// 
//	public void setId(long id) {
//		mId = id;
//	}
// 
//	public String getName() {
//		return mName;
//	}
// 
//	public void setName(String name) {
//		mName = name;
//	}
// 
//	public String getAddress() {
//		return mAddress;
//	}
// 
//	public void setAddress(String address) {
//		mAddress = address;
//	}
// 
//	public String getCity() {
//		return mCity;
//	}
// 
//	public void setCity(String city) {
//		mCity = city;
//	}
// 
//	public String getState() {
//		return mState;
//	}
// 
//	public void setState(String state) {
//		mState = state;
//	}
// 
//	public String getZip() {
//		return mZip;
//	}
// 
//	public void setZip(String zip) {
//		mZip = zip;
//	}
// 
//	public String getPhone() {
//		return mPhone;
//	}
// 
//	public void setPhone(String phone) {
//		mPhone = phone;
//	}
// 
//	public int getLatitude() {
//		return mLatitude;
//	}
// 
//	public void setLatitude(int latitude) {
//		mLatitude = latitude;
//	}
// 
//	public int getLongitude() {
//		return mLongitude;
//	}
// 
//	public void setLongitude(int longitude) {
//		mLongitude = longitude;
//	}
// 
//	public float getDistance() {
//		return mDistance;
//	}
// 
//	public void setDistance(float distance) {
//		mDistance = distance;
//	}
// 
//	@Override
//	public boolean equals(Object o) {
//		if (o instanceof Venue && ((Venue) o).getId() == mId) {
//			return true;
//		}
//		return false;
//	}
// 
//	@Override
//	public int hashCode() {
//		return Long.valueOf(mId).hashCode();
//	}
// 
//	public int getPcode() {
//		return mPcode;
//	}
// 
//	public void setPcode(int pcode) {
//		mPcode = pcode;
//	}
// 
//	public String getImageUrl() {
//		return mImageUrl;
//	}
// 
//	public void setImageUrl(String imageUrl) {
//		mImageUrl = imageUrl;
//	}
 
}