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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {

    private int size = 0;
    private boolean flag = false;
    private Country[] records;

    public Data() {
        setFlag(false);
        numberOfrecords("WDI.csv");
    }

    public void ReadData(String filename) {
        records = new Country[size];
        String line = "";
        BufferedReader br = null;
        int x = 0;
        try {
            br = new BufferedReader(new FileReader(filename));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].trim().isEmpty()) {
                    break;
                }
                if (flag) {
                    Country country = new Country();

                    country.setName(record[0]);
                    country.setSeriesName(record[1]);

                    int index = 0;
                    for (int i = 2; i < record.length; i++) {
                        if (checkData(record[i])) {
                           country.addByIndex(index, 0.0);
                        } else {
                            country.addByIndex(index, Double.parseDouble(record[i]));
                        }
                        index++;
                    }
                    records[x] = country;
                    x++;
                }else {
                    size++;
                }

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tTask completed...");
        }
    }

    public int numberOfrecords(String filename) {
        size = 0;
        ReadData(filename);
        return size;
    }


    public boolean checkData(String year) {
        if (year.trim().equals("..")) {
            return true;
        }
        return false;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        if (!flag) {
            System.out.println("counting record");
        } else {
            System.out.println("getting records");
        }
    }

    public Country[] getRecords() {
        return records;
    }

    public int getSize() {
        return size;
    }
}

