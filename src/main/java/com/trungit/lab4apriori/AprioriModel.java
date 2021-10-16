/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 *
 * @author ThanhTrungK15
 */
public class AprioriModel {
    private DataSource dtsource;
    private Instances dataset;
    private Apriori apriori;
    private String[] modelConfig, agrsFilterConfig;
    
    public AprioriModel() {} 
    
    public AprioriModel(String filename) throws Exception {
        this.dtsource = new DataSource(filename);
        this.dataset = dtsource.getDataSet();
        this.apriori = new Apriori();
    }

    public void convertNumericToNominal(String agrsFilterConfig) throws Exception {
        this.agrsFilterConfig = weka.core.Utils.splitOptions(agrsFilterConfig);
        NumericToNominal numericToNominal = new NumericToNominal();
        
        numericToNominal.setOptions(this.agrsFilterConfig);
        numericToNominal.setInputFormat(dataset);
        
        /* Áp dụng bộ lọc cho tập dữ liệu và ghi đè bộ dữ liệu cũ */
        this.dataset =  Filter.useFilter(dataset, numericToNominal);
    }
    
    public void mineAprioriRules(String modelConfig) throws Exception {
         /* Tạo tham số tinh chỉnh mô hình từ chuỗi tham số truyền vào */
        this.modelConfig = weka.core.Utils.splitOptions(modelConfig);
        
        apriori.setOptions(this.modelConfig);
        apriori.buildAssociations(dataset);
    }
    
    public String printDataset() {
        return dataset.toString();
    }
    
    @Override
    public String toString() {
        return apriori.toString();
    }
}
