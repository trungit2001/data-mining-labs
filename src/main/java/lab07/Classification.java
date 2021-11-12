/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab07;

import java.io.File;
import java.io.IOException;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;

/**
 *
 * @author ThanhTrungK15
 */
public abstract class Classification {
    protected Instances trainSet, testSet;
    protected Evaluation eval;
    protected String[] modelOptions;
    
    public Classification() {}
    
    public Instances loadARFF(String pathFileToLoad) throws IOException {
        ArffLoader arffLoader = new ArffLoader();
        arffLoader.setFile(new File(pathFileToLoad));
        return arffLoader.getDataSet();
    }
    
    public void saveARFF(Instances dataset, String pathFileToSave) throws IOException {
        ArffSaver arffSaver = new ArffSaver();
        arffSaver.setInstances(dataset);
        arffSaver.setFile(new File(pathFileToSave));
        arffSaver.writeBatch();
    }
    
    public Instances loadCSV(String pathFileToLoad) throws IOException {
        CSVLoader csvLoader = new CSVLoader();
        csvLoader.setFile(new File(pathFileToLoad));
        return csvLoader.getDataSet();
    }
    
    public void saveCSV(Instances dataset, String pathFileToSave) throws IOException {
        CSVSaver csvSaver = new CSVSaver();
        csvSaver.setInstances(dataset);
        csvSaver.setFile(new File(pathFileToSave));
        csvSaver.writeBatch();
    }
    
    public void loadTrainSet(Instances dataset) {
        trainSet = dataset;
    }
    
    public void loadTestSet(Instances dataset) {
        testSet = dataset;
    }
    
    public abstract void fit(String modelOptions) throws Exception;
    
    public abstract void crossValidation(int k, int seed) throws Exception;
    
    public abstract void evalPerformance() throws Exception;
    
    public abstract Instances predict(Instances newDataset) throws Exception;
    
    public String printEvalResult() throws Exception {
        return String.format("%s\n%s", eval.toSummaryString(), eval.toMatrixString());
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
