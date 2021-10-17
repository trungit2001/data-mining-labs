/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.trungit.lab4apriori;

/**
 *
 * @author ThanhTrungK15
 */
public class TestModel {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        /* Đường dẫn đến file dữ liệu cần mở, nhớ đặt 2 dấu "\" */
        String pathFile = "D:\\Codes\\DataWeka\\apriori.arff";
//        String pathFile = "D:\\Codes\\DataWeka\\weather.numeric.arff";
        
        /* Các tham số tinh chỉnh cho mô hình Apriori, FP-Growth và convertNumericToNominal */
        String aprioriModelConfig = "-N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S -1.0 -c -1";
        String fpGrowthModelConfig = "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1";
        String agrsFilterConfig = "-R first-last";
                
        /* Mô hình khai thác dữ liệu theo Apriori rule */
//        AprioriModel aprioriModel = new AprioriModel(pathFile);
//        aprioriModel.convertNumericToNominal(agrsFilterConfig);
//        aprioriModel.mineAprioriRules(aprioriModelConfig);
//        
//        System.out.println(aprioriModel.printDataset());
//        System.out.println(aprioriModel.toString());
        
        /* Mô hình khai thác dữ liệu theo FP-Growth */
        FPGrowthModel fpGrowthModel = new FPGrowthModel(pathFile);
        fpGrowthModel.mineFPGrowthRules(fpGrowthModelConfig);
        System.out.println(fpGrowthModel.printDataset());
        System.err.println(fpGrowthModel.toString());
    }
    
}
