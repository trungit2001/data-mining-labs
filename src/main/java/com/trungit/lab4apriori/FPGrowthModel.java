/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import java.io.File;
import java.io.IOException;
import weka.associations.FPGrowth;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author ThanhTrungK15
 */
public class FPGrowthModel {
    private DataSource dtsource;
    
    /* Load and save *.arff file */
    private ArffLoader arffLoader;
    private ArffSaver arffSaver;
    
    /* Load and save *.csv file*/
    private CSVLoader csvLoader;
    private CSVSaver csvSaver;
    
    private Instances dataset;
    private final FPGrowth fpGrowth;
    
    public  FPGrowthModel() {
        fpGrowth = new FPGrowth();
    }
    
    public FPGrowthModel(String pathFile) throws Exception {
        dtsource = new DataSource(pathFile);
        dataset = dtsource.getDataSet();
        fpGrowth = new FPGrowth();
    }
    
    /**
     * 
     * Đọc dữ liệu dạng file *.arff lên chương trình
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.arff cần tải lên
     * @throws IOException 
     */
    public void loadARFF(String pathFileToLoad) throws IOException {
        arffLoader = new ArffLoader();
        arffLoader.setFile(new File(pathFileToLoad));
        dataset = arffLoader.getDataSet();
    }
    
    /**
     * 
     * Lưu file dữ liệu dạng *.arff xuống ổ cứng
     * 
     * @param pathFileToSave Đường dẫn đến file *.arff cần lưu
     * @throws IOException 
     */
    public void saverARFF(String pathFileToSave) throws IOException {
        arffSaver = new ArffSaver();
        arffSaver.setInstances(dataset);
        arffSaver.setFile(new File(pathFileToSave));
        arffSaver.writeBatch();
    }
    
    /**
     * 
     * Đọc dữ liệu dạng file *.csv lên chương trình
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.csv cần tải lên
     * @throws Exception 
     */
    public void loadCSV(String pathFileToLoad) throws Exception {
        csvLoader = new CSVLoader();
        csvLoader.setSource(new File(pathFileToLoad));
        dataset = csvLoader.getDataSet();
    }
          
    /**
     * 
     * Lưu file dữ liệu dạng *.csv xuống ổ cứng
     * 
     * @param pathFileToSave Đường dẫn đến file *.csv cần lưu
     * @throws IOException 
     */
    public void saveCSV(String pathFileToSave) throws IOException {
        csvSaver = new CSVSaver();
        csvSaver.setInstances(dataset);
        
        csvSaver.setFile(new File(pathFileToSave));
        csvSaver.writeBatch();
    }
    
    /**
     * 
     * Loại bỏ các thuộc tính dư thừa theo tham số truyền vào
     * 
     * @param argsRemoveFilterConfig Chuỗi tham số tinh chỉnh cho bộ lọc Remove
     * của Weka
     * @throws Exception 
     */
    public void remove(String argsRemoveFilterConfig) throws Exception {
        String[] argsFilter = weka.core.Utils.splitOptions(argsRemoveFilterConfig);
        Remove removeFilter = new Remove();
        
        removeFilter.setOptions(argsFilter);
        removeFilter.setInputFormat(dataset);
        
        /* Áp dụng bộ lọc và ghi đè lại dữ liệu cũ */
        dataset = Filter.useFilter(dataset, removeFilter);
    }
    
    /**
     * 
     * Chuyển thuộc tính dạng Nominal sang Binary
     * 
     * @param argsNominalToBinaryFilterConfig Chuỗi tham số tinh chỉnh cho bộ lọc
     * NominalToBinary của Weka
     * @throws Exception 
     */
    public void convertNominalToBinary(String argsNominalToBinaryFilterConfig) throws Exception {
        String[] argsFilter = weka.core.Utils.splitOptions(argsNominalToBinaryFilterConfig);
        
        NominalToBinary nominalToBinaryFilter = new NominalToBinary();
        nominalToBinaryFilter.setOptions(argsFilter);
        nominalToBinaryFilter.setInputFormat(dataset);
        
        dataset = Filter.useFilter(dataset, nominalToBinaryFilter);
    }
    
    
    
    /**
     * 
     * Khai thác luật kết hợp với thuật toán FP-Growth
     * 
     * @param argsModelConfig Chuỗi thông số tinh chỉnh cho mô hình FP-Growth
     * @throws Exception 
     */
    public void mineFPGrowthRules(String argsModelConfig) throws Exception {
        String[] modelConfig = weka.core.Utils.splitOptions(argsModelConfig);
        
        fpGrowth.setOptions(modelConfig);
        fpGrowth.buildAssociations(dataset);
    }

    /**
     * 
     * @return Bộ dữ liệu
     */
    public String printDataset() {
        return dataset.toString();
    }
    
    /**
     * 
     * @return Các luật kết hợp tìm được với thuật toán Apriori
     */
    @Override
    public String toString() {
        return fpGrowth.toString();
    }
}
