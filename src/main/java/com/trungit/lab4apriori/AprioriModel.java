/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import weka.associations.Apriori;

/**
 *
 * @author ThanhTrungK15
 */
public class AprioriModel extends Models {
    private final Apriori apriori;
    
    /**
     * 
     * Constructor mặc định
     */
    public AprioriModel() {
        apriori = new Apriori();
    }
    
    /**
     * 
     * Constructor có tham số
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.arff cần tải lên
     * @throws Exception 
     */
    public AprioriModel(String pathFileToLoad) throws Exception {
        super.loadARFF(pathFileToLoad);
        apriori = new Apriori();
    }
            
    /**
     * 
     * Khai thác luật kết hợp với thuật toán Apriori
     * 
     * @param argsModelCfg Chuỗi thông số tinh chỉnh cho mô hình Apriori
     * @throws java.lang.Exception
     */
    @Override
    public void mineRules(String argsModelCfg) throws Exception {
        String[] options = weka.core.Utils.splitOptions(argsModelCfg);
        apriori.setOptions(options);
        apriori.buildAssociations(dataset);
    }
    
    /**
     * 
     * @return Các luật kết hợp tìm được với thuật toán Apriori
     */
    @Override
    public String toString() {
        return apriori.toString();
    }
}
