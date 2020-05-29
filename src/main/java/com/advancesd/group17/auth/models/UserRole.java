package com.advancesd.group17.auth.models;

public class UserRole {
    private String bannerId;
    private long courseId;
    private long roleId;

    public UserRole() {
    }

    public UserRole(String bannerId, long courseId, long roleId) {
        this.bannerId = bannerId;
        this.courseId = courseId;
        this.roleId = roleId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String userId) {
        this.bannerId = userId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "bannerId=" + bannerId +
                ", courseId=" + courseId +
                ", roleId=" + roleId +
                '}';
    }
}
