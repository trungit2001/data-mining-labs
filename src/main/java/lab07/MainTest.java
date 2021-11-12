/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab07;

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
        /* Change base path to your save location */
        String basePath = "D:\\Codes\\DataWeka\\arff\\";
        String trainSet = basePath + "mushroom_train.arff";
        String testSet = basePath + "mushroom_test.arff";
        String newDataset = basePath + "mushroom_unlabel.arff";
        String resultDataset = basePath + "mushroom_predicted.arff";
        String modelPath = "D:\\Codes\\DataWeka\\models\\j48.model";
        
        /* Ex 1 */
        System.out.println("Ex 1");

        /* Naive Bayes model config */
        String nbConfig = "-batch-size 100";
        
        NaiveBayesClassifier nbModel = new NaiveBayesClassifier();
        
        nbModel.loadTrainSet(nbModel.loadARFF(trainSet));
        nbModel.loadTestSet(nbModel.loadARFF(testSet));
        
        nbModel.fit(nbConfig);
        nbModel.crossValidation(10, 1);
//        nbModel.evalPerformance();

        /* Predict new data */
        Instances predDatasetEx1 = nbModel.predict(nbModel.loadARFF(newDataset));
        /* Save predicted data */
        nbModel.saveARFF(predDatasetEx1, resultDataset);
        
        System.out.println(nbModel.toString());
        System.out.println(nbModel.printEvalResult());
        
        /* Ex 2*/
        System.out.println("Ex 2");

        /* Decision Tree model config */
        String dtConfig = "-C 0.25 -M 2";
        
        DecisionTreeClassifier dtModel = new DecisionTreeClassifier();
        
        dtModel.loadTrainSet(dtModel.loadARFF(trainSet));
        dtModel.loadTestSet(dtModel.loadARFF(testSet));
        
        /* Alert config for model */
        dtModel.fit(dtConfig);
        
        dtModel.crossValidation(10, 1);
        System.out.println("Testing on cross validation k-fold with k = 10");
        System.out.println(dtModel.printEvalResult());
        
        dtModel.evalPerformance();
        System.out.println("Testing on test set");
        System.out.println(dtModel.printEvalResult());
        
        /* Save model into disk */
        dtModel.saveModel(modelPath);

        /* Create a new model and load the trained model */
        DecisionTreeClassifier newDTModel = new DecisionTreeClassifier();
        
        /* Load model from disk */
        newDTModel.loadModel(modelPath);
        
        Instances predDatasetEx2 = newDTModel.predict(newDTModel.loadARFF(newDataset));
        newDTModel.saveARFF(predDatasetEx2, resultDataset);
        
        System.out.println(newDTModel.toString());

        /* Ex3 */
        System.out.println("Ex 3");
        /* KNN model config */
        
        String knnConfig = "-K 5 -W 0 -A \"weka.core.neighboursearch.LinearNNSearch -A \\\"weka.core.EuclideanDistance -R first-last\\\"\"";
        
        KNNClassifier knnModel = new KNNClassifier();
        
        knnModel.loadTrainSet(knnModel.loadARFF(trainSet));
        knnModel.loadTestSet(knnModel.loadARFF(testSet));
        
        /* Alert config for model */
        knnModel.fit(knnConfig);
        
        knnModel.crossValidation(10, 1);
        System.out.println("Testing on cross validation k-fold with k = 10");
        System.out.println(knnModel.printEvalResult());
        
        knnModel.evalPerformance();
        System.out.println("Testing on test set");
        System.out.println(knnModel.printEvalResult());
        
        Instances predDatasetEx3 = knnModel.predict(knnModel.loadARFF(newDataset));
        knnModel.saveARFF(predDatasetEx3, resultDataset);
        
        System.out.println(knnModel.toString());
    }
}
