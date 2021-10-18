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
     * @param argsFilterCfg Chuỗi tham số tinh chỉnh cho bộ lọc Remove
     * của Weka
     * @throws Exception 
     */
    public void remove(String argsFilterCfg) throws Exception {
        Remove rm = new Remove();
        String[] options = weka.core.Utils.splitOptions(argsFilterCfg);
        rm.setOptions(options);
        rm.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, rm);
    }
    
    /**
     * Loại bỏ các thuộc tính dư thừa bằng cách chọn theo biểu thức chính quy
     * (Regex)
     * @param argsFilterCfg Chuỗi tham số tinh chỉnh cho bộ lọc RemoveByName của
     * Weka
     * @throws Exception 
     */
    public void removeByName(String argsFilterCfg) throws Exception {
        RemoveByName rmByName = new RemoveByName();
        String[] options = weka.core.Utils.splitOptions(argsFilterCfg);
        rmByName.setOptions(options);
        rmByName.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, rmByName);
    }
    
    /**
     * 
     * Thay thế giá trị thiếu bằng giá trị người dùng nhập vào thông qua
     * tham số tinh chỉnh của bộ lọc
     * 
     * @param argsFilterCfg Chuỗi tham số tinh chỉnh bộ lọc
     * ReplaceMissingWithUserConstant của Weka
     * @throws Exception 
     */
    public void replaceMissingWithUserConstant(String argsFilterCfg) throws Exception {
        ReplaceMissingWithUserConstant rpConstant = new ReplaceMissingWithUserConstant();
        String[] options = weka.core.Utils.splitOptions(argsFilterCfg);
        rpConstant.setOptions(options);
        rpConstant.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, rpConstant);
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
        NumericToNominal numToNom = new NumericToNominal();
        String[] options = weka.core.Utils.splitOptions(argsFilterCfg);
        numToNom.setOptions(options);
        numToNom.setInputFormat(dataset);
        dataset =  Filter.useFilter(dataset, numToNom);
    }
    
    /**
     * 
     * Chuyển thuộc tính dạng Nominal sang Binary
     * 
     * @param argsFilterCfg Chuỗi tham số tinh chỉnh cho bộ lọc
     * NominalToBinary của Weka
     * @throws Exception 
     */
    public void convertNominalToBinary(String argsFilterCfg) throws Exception {
        NominalToBinary nomToBin = new NominalToBinary();
        String[] options = weka.core.Utils.splitOptions(argsFilterCfg);
        nomToBin.setOptions(options);
        nomToBin.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, nomToBin);
    }
    
    /**
     * 
     * Chuyển thuộc tính dạng Numeric sang Binary
     * 
     * @param argsFilterCfg Chuỗi tham số tinh chỉnh cho bộ lọc
     * NumericToBinary của Weka
     * @throws Exception 
     */
    public void convertNumericToBinary(String argsFilterCfg) throws Exception {
        NumericToBinary numToBin = new NumericToBinary();
        String[] options = weka.core.Utils.splitOptions(argsFilterCfg);
        numToBin.setOptions(options);
        numToBin.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, numToBin);
    }

    /**
     * Phương thức trừu tượng, yêu cầu ghi đè khi kế thừa lớp này
     * Khai thác luật kết hợp trên bộ dữ liệu
     * 
     * @param argsModelCfg Chuỗi thông số tinh chỉnh cho mô hình tương ứng của
     * lớp con kế thừa
     * 
     * @throws Exception
     */
    public abstract void mineRules(String argsModelCfg) throws Exception;
    
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
