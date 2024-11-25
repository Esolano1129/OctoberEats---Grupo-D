package org.example;

public class Restaurant {
    private int Id;
    private String RestaurantName;
    private String Address;
    private String Schedule;
    private String Rating;

    public Restaurant() {
        DBConextion NewConextion = new DBConextion();
        NewConextion.StablishConection();
    }

    public Restaurant(int id, String restaurantName, String address, String schedule, String rating) {
        DBConextion NewConextion = new DBConextion();
        NewConextion.StablishConection();
        this.Id = id;
        this.RestaurantName = restaurantName;
        this.Address = address;
        this.Schedule = schedule;
        this.Rating = rating;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getRestaurantName() {
        return this.RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.RestaurantName = restaurantName;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getSchedule() {
        return this.Schedule;
    }

    public void setSchedule(String schedule) {
        this.Schedule = schedule;
    }

    public String getRating() {
        return this.Rating;
    }

    public void setRating(String rating) {
        this.Rating = rating;
    }

    public void ReceiveNewOrders(){}

    public void UpdateMenu(){}

    public void UpdateAvailability(){}


}
