/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.trungit.lab4apriori;

/**
 *
 * @author ThanhTrungK15
 */
public class AprioriModelTest {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        /* Đường dẫn đến file dữ liệu cần mở, nhớ đặt 2 dấu "\" */
//        String pathFile = "D:\\Codes\\DataWeka\\apriori.arff";
        String pathFile = "D:\\Codes\\DataWeka\\weather.numeric.arff";
        
        /* Các tham số tinh chỉnh cho mô hình Apriori và convertNumericToNominal */
        String modelConfig = "-N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S -1.0 -c -1";
        String agrsFilterConfig = "-R first-last";
                
        AprioriModel aprioriModel = new AprioriModel(pathFile);
        aprioriModel.convertNumericToNominal(agrsFilterConfig);
        aprioriModel.mineAprioriRules(modelConfig);
        System.out.println(aprioriModel.toString());
    }
    
}
