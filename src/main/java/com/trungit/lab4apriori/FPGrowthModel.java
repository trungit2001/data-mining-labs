/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import weka.associations.FPGrowth;

/**
 *
 * @author ThanhTrungK15
 */
public class FPGrowthModel extends Models {
    private final FPGrowth fpGrowth;
    
    /**
     * 
     * Constructor mặc định
     */
    public  FPGrowthModel() {
        fpGrowth = new FPGrowth();
    }
    
    /**
     * 
     * Constructor có tham số
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.arff cần tải lên
     * @throws Exception 
     */
    public FPGrowthModel(String pathFileToLoad) throws Exception {
        super.loadARFF(pathFileToLoad);
        fpGrowth = new FPGrowth();
    }
    
    /**
     * 
     * Khai thác luật kết hợp với thuật toán FP-Growth
     * 
     * @param argsModelCfg Chuỗi thông số tinh chỉnh cho mô hình FP-Growth
     * @throws Exception 
     */
    @Override
    public void mineRules(String argsModelCfg) throws Exception {
        String[] options = weka.core.Utils.splitOptions(argsModelCfg);
        fpGrowth.setOptions(options);
        fpGrowth.buildAssociations(dataset);
    }

    /**
     * 
     * @return Các luật kết hợp tìm được với thuật toán FP-Growth
     */
    @Override
    public String toString() {
        return fpGrowth.toString();
    }
}
