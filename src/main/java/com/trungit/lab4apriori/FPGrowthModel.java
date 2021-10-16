/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import weka.associations.FPGrowth;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author ThanhTrungK15
 */
public class FPGrowthModel {
    private DataSource dtsource;
    private Instances dataset;
    private FPGrowth fpGrowth;
    private String[] modelConfig, argsFilterConfig;
    
    public  FPGrowthModel() {}
    
    public FPGrowthModel(String pathFile) throws Exception {
        this.dtsource = new DataSource(pathFile);
        this.dataset = dtsource.getDataSet();
        this.fpGrowth = new FPGrowth();
    }
    
    public void mineFPGrowthRules(String modelConfig) throws Exception {
        this.modelConfig = weka.core.Utils.splitOptions(modelConfig);
        
        fpGrowth.setOptions(this.modelConfig);
        fpGrowth.buildAssociations(dataset);
    }

    public String printDataset() {
        return dataset.toString();
    }
    
    @Override
    public String toString() {
        return fpGrowth.toString();
    }
    
}
