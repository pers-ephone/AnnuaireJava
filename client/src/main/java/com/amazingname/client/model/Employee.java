package com.amazingname.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String landlinePhone;
    private String mobilePhone;
    private Site site;
    private Service service;

    public Employee() {}

    public Employee(Integer id, String firstName, String lastName, String email,
                    String landlinePhone, String mobilePhone,
                    Site site, Service service) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.landlinePhone = landlinePhone;
        this.mobilePhone = mobilePhone;
        this.site = site;
        this.service = service;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLandlinePhone() { return landlinePhone; }
    public void setLandlinePhone(String landlinePhone) { this.landlinePhone = landlinePhone; }

    public String getMobilePhone() { return mobilePhone; }
    public void setMobilePhone(String mobilePhone) { this.mobilePhone = mobilePhone; }

    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }

    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }

}
