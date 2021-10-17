/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import java.io.File;
import java.io.IOException;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.RemoveByName;

/**
 *
 * @author ThanhTrungK15
 */
public class AprioriModel {
    private DataSource dtsource;
    
    /* Load and save *.arff file */
    private ArffLoader arffLoader;
    private ArffSaver arffSaver;
    
    /* Load and save *.csv file*/
    private CSVLoader csvLoader;
    private CSVSaver csvSaver;
    
    private Instances dataset;
    private Apriori apriori;
    
    /**
     * 
     * Constructor mặc định
     */
    public AprioriModel() {} 
    
    /**
     * 
     * Constructor có tham số
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.arff cần tải lên
     * @throws Exception 
     */
    public AprioriModel(String pathFileToLoad) throws Exception {
        dtsource = new DataSource(pathFileToLoad);
        dataset = dtsource.getDataSet();
        apriori = new Apriori();
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
     * @param argsFilterCfg Chuỗi tham số tinh chỉnh cho bộ lọc Remove
     * của Weka
     * @throws Exception 
     */
    public void remove(String argsFilterCfg) throws Exception {
        Remove removeFilter = new Remove();
        String[] argsFilter = weka.core.Utils.splitOptions(argsFilterCfg);

        removeFilter.setOptions(argsFilter);
        removeFilter.setInputFormat(dataset);
        
        /* Áp dụng bộ lọc và ghi đè lại dữ liệu cũ */
        dataset = Filter.useFilter(dataset, removeFilter);
    }
    
    public void removeByName(String argsFilterCfg) throws Exception {
        RemoveByName removeByNameFilter = new RemoveByName();
        String[] argsFilter = weka.core.Utils.splitOptions(argsFilterCfg);
        
    }
    
    
    /**
     * 
     * Chuyển đổi thuộc tính số Numeric sang thuộc tính rời rạc Nominal
     * 
     * @param argsFilterCfg Chuỗi thông số tinh chỉnh cho bộ lọc
     * NumericToNominal của Weka
     * @throws java.lang.Exception
     */
    public void convertNumericToNominal(String argsFilterCfg) throws Exception {
        NumericToNominal numericToNominalFilter = new NumericToNominal();
        String[] argsFilter = weka.core.Utils.splitOptions(argsFilterCfg);
        
        numericToNominalFilter.setOptions(argsFilter);
        numericToNominalFilter.setInputFormat(dataset);
        
        /* Áp dụng bộ lọc và ghi đè lại dữ liệu cũ */
        dataset =  Filter.useFilter(dataset, numericToNominalFilter);
    }
        
    /**
     * 
     * Khai thác luật kết hợp với thuật toán Apriori
     * 
     * @param argsModelCfg Chuỗi thông số tinh chỉnh cho mô hình Apriori
     * @throws java.lang.Exception
     */
    public void mineAprioriRules(String argsModelCfg) throws Exception {
        String[] argsModel = weka.core.Utils.splitOptions(argsModelCfg);
        
        apriori.setOptions(argsModel);
        apriori.buildAssociations(dataset);
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
        return apriori.toString();
    }
}