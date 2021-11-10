/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab06;

import weka.core.Instances;

/**
 *
 * @author ThanhTrungK15
 */
public class MainTest {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        String basePath = "D:\\Codes\\DataWeka\\arff\\";
        String trainSet = basePath + "mushroom_train.arff";
        String testSet = basePath + "mushroom_test.arff";
        String newDataset = basePath + "mushroom_new.arff";
        String resultDataset = basePath + "mushroom_pred.arff";
        
        NaiveBayesClassifier nb = new NaiveBayesClassifier();
        nb.loadTrainSet(nb.loadARFF(trainSet));
        nb.loadTestSet(nb.loadARFF(testSet));
        
        nb.fit();
        nb.crossValidation(10, 1);
//        nb.evalPerformance();
        
        Instances predDataset = nb.predict(nb.loadARFF(newDataset));
        nb.saveARFF(predDataset, resultDataset);
        
        System.out.println(nb.printEvalResult());
    }
}
