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
    private Instances dataset, newDataset;
    private Apriori apriori;
    private String[] modelConfig, agrsFilterConfig;
    
    public AprioriModel() {} 
    
    public AprioriModel(String filename) throws Exception {
        this.dtsource = new DataSource(filename);
        this.dataset = dtsource.getDataSet();
        this.apriori = new Apriori();
    }

    public Instances convertNumericToNominal(Instances dataset, String agrsFilterConfig) throws Exception {
        this.agrsFilterConfig = weka.core.Utils.splitOptions(agrsFilterConfig);
        NumericToNominal numericToNominal = new NumericToNominal();
        
        numericToNominal.setOptions(this.agrsFilterConfig);
        numericToNominal.setInputFormat(dataset);
        
        return Filter.useFilter(dataset, numericToNominal);
    }
    
    public void mineAprioriRules(String modelConfig, String agrsFilterConfig) throws Exception {
        /* Gọi phương thức để convert Numeric về Nominal và gán vào đối tượng mới */
        this.newDataset = convertNumericToNominal(dataset, agrsFilterConfig);
        
        /* Tạo tham số tinh chỉnh mô hình từ chuỗi tham số truyền vào */
        this.modelConfig = weka.core.Utils.splitOptions(modelConfig);
        
        apriori.setOptions(this.modelConfig);
        apriori.buildAssociations(newDataset);
    }
    
    @Override
    public String toString() {
        return apriori.toString();
    }
}
