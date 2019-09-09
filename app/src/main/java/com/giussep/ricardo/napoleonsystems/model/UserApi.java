package com.giussep.ricardo.napoleonsystems.model;

import com.google.gson.annotations.SerializedName;

public class UserApi {
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    private String email;

    private Address address;

    private String phone;

    private String website;

    private Company company;

    public UserApi(UserDto userDto) {
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.username = userDto.getUsername();
        this.email = userDto.getEmail();

        Address address = new Address();
        address.setStreet(userDto.getStreet());
        address.setSuite(userDto.getSuite());
        address.setCity(userDto.getCity());
        address.setZipcode(userDto.getZipcode());

        Geo geo = new Geo();
        geo.setLat(userDto.getLat());
        geo.setLng(userDto.getLng());

        address.setGeo(geo);

        this.address = address;
        this.phone = userDto.getPhone();
        this.website = userDto.getWebsite();

        Company company = new Company();
        company.setName(userDto.getCompanyName());
        company.setCatchPhrase(userDto.getCatchPhrase());
        company.setBs(userDto.getBs());

        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


}
