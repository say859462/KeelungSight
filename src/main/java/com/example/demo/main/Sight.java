package com.example.demo.main;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(collection = "SightInfo")
public class Sight implements Serializable {
    private String sightName;//The name of sight
    private String zone;//The zone of sight
    private String category;//The category of sight
    private String photoURL ;//The photo url of sight picture
    private String description;//The description of the sight
    private  String address;//The address of the sight


    //Declare a Null Constructor
    public Sight(){
    }

    //Constructor with parameter
    public Sight(String sightName,String zone,String category,String photoURL,String address,String description){
        this.sightName=sightName;
        this.zone=zone;
        this.category=category;
        this.photoURL=photoURL;
        this.address=address;
        this.description=description;
    }

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String toString(){
        return String.format(
                "SightName: " + this.sightName + '\n' +
                        "Zone:" + this.zone + '\n'+
                        "Category:" + this.category + '\n' +
                        "PhotoURL:" + this.photoURL + '\n' +
                        "Description:" + this.description + '\n'+
                        "Address:" + this.address +'\n'
        );
    }

}
