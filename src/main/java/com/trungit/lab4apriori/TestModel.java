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
//        String pathFileToLoad = "D:\\Codes\\DataWeka\\arff\\apriori.arff";
//        String pathFileToLoad = "D:\\Codes\\DataWeka\\arff\\weather.numeric.arff";
        
        /* Đọc file *.csv */
        String pathFileToLoad = "D:\\Codes\\DataWeka\\csv\\apriori.csv";

        /* Lưu vào đường dẫn này */
//        String pathFileToWrite = "D:\\Codes\\DataWeka\\csv\\apriori_preprocessed.csv";
        String pathFileToWrite = "D:\\Codes\\DataWeka\\arff\\apriori_preprocessed.arff";

        /* Các tham số tinh chỉnh cho mô hình Apriori và FP-Growth */
//        String aprioriModelConfig = "-N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S -1.0 -c -1";
//        String fpGrowthModelConfig = "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1";
        
        /* Các tham số tinh chỉnh cho bộ lọc */
//        String argsNumericToNominalFilterConfig = "-R first-last";
        String argsRemoveFilterConfig = "-R 1";
        
        /* Mô hình khai thác dữ liệu theo Apriori rule */
        AprioriModel aprioriModel = new AprioriModel();
        aprioriModel.loadCSV(pathFileToLoad);
        
        /* In tập dữ liệu trước khi tiền xử lý */
        System.out.println(aprioriModel.printDataset());
        
        aprioriModel.remove(argsRemoveFilterConfig);
        
        /* In tập dữ liệu sau khi tiền xử lý */
        System.out.println(aprioriModel.printDataset());

        /* Lưu dữ liệu đã tiền xử lý vào file */
        aprioriModel.saverARFF(pathFileToWrite);
        System.out.println("File save success!");
    }
    
}
