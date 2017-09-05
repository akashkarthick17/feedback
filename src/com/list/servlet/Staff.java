package com.list.servlet;

/**
 * Created by Akash on 05-09-2017.
 */
public class Staff {

    private String staffName, subjectCode, subjectName;

    public Staff(String staffName, String subjectCode, String subjectName) {
        this.staffName = staffName;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }

    public Staff() {
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
