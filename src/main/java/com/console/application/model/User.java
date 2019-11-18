package com.console.application.model;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class User {

    private Integer _id;
    private String url;
    private String external_id;
    private String name;
    private String alias;
    private String created_at;
    private Boolean active;
    private Boolean verified;
    private Boolean shared;
    private Locale locale;
    private TimeZone timezone;
    private String last_login_at;
    private String email;
    private String phone;
    private String signature;
    private Integer organization_id;
    private List<String> tags;
    private Boolean suspended;
    private String role;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getCreated_at() {
        return created_at;
    }

    public TimeZone getTimezone() {
        return timezone;
    }

    public void setTimezone(TimeZone timezone) {
        this.timezone = timezone;
    }

    public String getLast_login_at() {
        return last_login_at;
    }

    public void setLast_login_at(String last_login_at) {
        this.last_login_at = last_login_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", url='" + url + '\'' +
                ", external_id='" + external_id + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", created_at='" + created_at + '\'' +
                ", active=" + active +
                ", verified=" + verified +
                ", shared=" + shared +
                ", locale=" + locale +
                ", timezone=" + timezone +
                ", last_login_at='" + last_login_at + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", signature='" + signature + '\'' +
                ", organization_id=" + organization_id +
                ", tags=" + tags +
                ", suspended=" + suspended +
                ", role='" + role + '\'' +
                '}';
    }
}
