/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.trungit.lab4apriori;

/**
 *
 * @author ThanhTrungK15
 */
public class TestModels {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            /* Đường dẫn đến file dữ liệu cần mở, nhớ đặt 2 dấu "\" */
            String pathFileToLoad = "D:\\Codes\\DataWeka\\arff\\supermarket.arff";

           /* Đọc file *.csv */
//           String pathFileToLoad = "D:\\Codes\\DataWeka\\csv\\apriori.csv";

           /* Lưu vào đường dẫn này */
//           String pathFileToWrite = "D:\\Codes\\DataWeka\\arff\\apriori_preprocessed.arff";

           /* Các tham số tinh chỉnh cho mô hình Apriori và FP-Growth */
            String arModelCfg = "-N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S -1.0 -c -1";
            String fpModelCfg = "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1";
            
            /* Các tham số tinh chỉnh cho các bộ lọc */
//            String argsRemoveFilterCfg = "-R 1";
            String argsRemoveByNameFilterCfg = "-E department[0-9]+";
            String argsReplaceMissingFilterCfg = "-A first-last -N f -R 0 -F \"yyyy-MM-dd\\'T\\'HH:mm:ss\"";
            String argsNominalToBinaryFilterCfg = "-N -R first-last";

           /* Bài 1 */
            AprioriModel arModel = new AprioriModel(pathFileToLoad);
            arModel.removeByName(argsRemoveByNameFilterCfg);
            arModel.mineRules(arModelCfg);
            
            System.out.println(arModel.toString());
                 
           /* Bài 2 */
            FPGrowthModel fpModel = new FPGrowthModel(pathFileToLoad);
            fpModel.removeByName(argsRemoveByNameFilterCfg);
            fpModel.replaceMissingWithUserConstant(argsReplaceMissingFilterCfg);
            fpModel.convertNominalToBinary(argsNominalToBinaryFilterCfg);
            fpModel.mineRules(fpModelCfg);

            System.out.println(fpModel.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
