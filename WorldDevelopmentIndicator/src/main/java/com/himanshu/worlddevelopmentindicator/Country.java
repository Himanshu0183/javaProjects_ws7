/**********************************************
 Workshop 7
 Course: JAC 444 - Summer 2022
 Last Name: Himanshu
 First Name: Himanshu
 ID: 146109202
 Section: ZBB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature  Himanshu
 Date: 07/23/2022
 **********************************************/

package com.himanshu.worlddevelopmentindicator;

import java.util.Arrays;

public class Country {
    private String name;
    private String seriesName;
    private String m_years_value;
    private double [] years;

    public Country() {
        years =  new double[11];
    }

    public Country(String name, String seriesName, double[] years) {
        this.name = name;
        this.seriesName = seriesName;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getM_years_value() {
        return Arrays.toString(years);
    }

    public void setM_years_value(String m_years_value) {
        this.m_years_value = m_years_value;
    }

    public double[] getYears() {
        return years;
    }

    public void setYears(double[] years) {
        this.years = years;
    }
    public void addByIndex(int index, double data) {
        this.years[index] = data;
    }


    public double getYearByIndex(int index) {
        return this.years[index] ;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", years=" + Arrays.toString(years) +
                '}';
    }
}
