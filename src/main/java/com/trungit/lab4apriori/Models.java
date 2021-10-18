/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trungit.lab4apriori;

import java.io.File;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.NumericToBinary;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.RemoveByName;
import weka.filters.unsupervised.attribute.ReplaceMissingWithUserConstant;

/**
 *
 * @author ThanhTrungK15
 */
public abstract class Models {
    protected Instances dataset;
    String[] modelOptions, filterOptions;
    
    public Models() {}
    
    /**
     * 
     * Đọc dữ liệu dạng file *.arff lên chương trình
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.arff cần tải lên
     * @throws IOException 
     */
    public void loadARFF(String pathFileToLoad) throws IOException {
        ArffLoader arffLoader = new ArffLoader();
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
    public void saveARFF(String pathFileToSave) throws IOException {
        ArffSaver arffSaver = new ArffSaver();
        arffSaver.setInstances(dataset);
        arffSaver.setFile(new File(pathFileToSave));
        arffSaver.writeBatch();
    }
    
    /**
     * 
     * Đọc dữ liệu dạng file *.csv lên chương trình
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.csv cần tải lên
     * @throws IOException 
     */
    public void loadCSV(String pathFileToLoad) throws IOException {
        CSVLoader csvLoader = new CSVLoader();
        csvLoader.setFile(new File(pathFileToLoad));
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
        CSVSaver csvSaver = new CSVSaver();
        csvSaver.setInstances(dataset);
        csvSaver.setFile(new File(pathFileToSave));
        csvSaver.writeBatch();
    }
    
    /**
     * 
     * Loại bỏ các thuộc tính dư thừa theo tham số truyền vào
     * 
     * @param filterOptions Chuỗi tham số tinh chỉnh cho bộ lọc Remove
     * của Weka
     * @throws Exception 
     */
    public void remove(String filterOptions) throws Exception {
        Remove rm = new Remove();
        this.filterOptions = weka.core.Utils.splitOptions(filterOptions);
        rm.setOptions(this.filterOptions);
        rm.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, rm);
    }
    
    /**
     * Loại bỏ các thuộc tính dư thừa bằng cách chọn theo biểu thức chính quy
     * (Regex)
     * @param filterOptions Chuỗi tham số tinh chỉnh cho bộ lọc RemoveByName của
     * Weka
     * @throws Exception 
     */
    public void removeByName(String filterOptions) throws Exception {
        RemoveByName rmByName = new RemoveByName();
        this.filterOptions = weka.core.Utils.splitOptions(filterOptions);
        rmByName.setOptions(this.filterOptions);
        rmByName.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, rmByName);
    }
    
    /**
     * 
     * Thay thế giá trị thiếu bằng giá trị người dùng nhập vào thông qua
     * tham số tinh chỉnh của bộ lọc
     * 
     * @param filterOptions Chuỗi tham số tinh chỉnh bộ lọc
     * ReplaceMissingWithUserConstant của Weka
     * @throws Exception 
     */
    public void replaceMissingWithUserConstant(String filterOptions) throws Exception {
        ReplaceMissingWithUserConstant rpConstant = new ReplaceMissingWithUserConstant();
        this.filterOptions = weka.core.Utils.splitOptions(filterOptions);
        rpConstant.setOptions(this.filterOptions);
        rpConstant.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, rpConstant);
    }
    
    /**
     * 
     * Chuyển đổi thuộc tính số Numeric sang thuộc tính rời rạc Nominal
     * 
     * @param filterOptions Chuỗi thông số tinh chỉnh cho bộ lọc
     * NumericToNominal của Weka
     * @throws java.lang.Exception
     */
    public void convertNumericToNominal(String filterOptions) throws Exception {
        NumericToNominal numToNom = new NumericToNominal();
        this.filterOptions = weka.core.Utils.splitOptions(filterOptions);
        numToNom.setOptions(this.filterOptions);
        numToNom.setInputFormat(dataset);
        dataset =  Filter.useFilter(dataset, numToNom);
    }
    
    /**
     * 
     * Chuyển thuộc tính dạng Nominal sang Binary
     * 
     * @param filterOptions Chuỗi tham số tinh chỉnh cho bộ lọc
     * NominalToBinary của Weka
     * @throws Exception 
     */
    public void convertNominalToBinary(String filterOptions) throws Exception {
        NominalToBinary nomToBin = new NominalToBinary();
        this.filterOptions = weka.core.Utils.splitOptions(filterOptions);
        nomToBin.setOptions(this.filterOptions);
        nomToBin.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, nomToBin);
    }
    
    /**
     * 
     * Chuyển thuộc tính dạng Numeric sang Binary
     * 
     * @param filterOptions Chuỗi tham số tinh chỉnh cho bộ lọc
     * NumericToBinary của Weka
     * @throws Exception 
     */
    public void convertNumericToBinary(String filterOptions) throws Exception {
        NumericToBinary numToBin = new NumericToBinary();
        this.filterOptions = weka.core.Utils.splitOptions(filterOptions);
        numToBin.setOptions(this.filterOptions);
        numToBin.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, numToBin);
    }

    /**
     * Phương thức trừu tượng, yêu cầu ghi đè khi kế thừa lớp này
     * Khai thác luật kết hợp trên bộ dữ liệu
     * 
     * @param modelOptions Chuỗi thông số tinh chỉnh cho mô hình tương ứng của
     * lớp con kế thừa
     * 
     * @throws Exception
     */
    public abstract void mineRules(String modelOptions) throws Exception;
    
    /**
     * 
     * @return Bộ dữ liệu
     */
    public String printDataset() {
        return dataset.toString();
    }
    
    /**
     * 
     * @return Tóm tắt bộ dữ liệu
     */
    public String printSummaryDataset() {
        return dataset.toSummaryString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
