/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
     * @param modelOptions Chuỗi thông số tinh chỉnh cho mô hình Apriori
     * @throws java.lang.Exception
     */
    @Override
    public void mineRules(String modelOptions) throws Exception {
        super.modelOptions = weka.core.Utils.splitOptions(modelOptions);
        apriori.setOptions(super.modelOptions);
        apriori.buildAssociations(dataset);
    }
    
    /**
     * 
     * Xuất kết quả khai thác luật kết hợp theo Apriori ra file
     * 
     * @param pathFileToWriteResult Đường dẫn đến file lưu kết quả trên ổ đĩa
     * @throws IOException 
     */
    @Override
    public void saveResultMines(String pathFileToWriteResult) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFileToWriteResult))) {
            bw.write(toString());
        } catch (Exception e) {
            throw(e);
        }
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
