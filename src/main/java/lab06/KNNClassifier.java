/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab06;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Debug.Random;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author ThanhTrungK15
 */
public class KNNClassifier extends Classification {
    IBk knn;
    
    public KNNClassifier() {
        knn = new IBk();
    }
    
    @Override
    public void fit(String modelOptions) throws Exception {
        super.trainSet.setClassIndex(super.trainSet.numAttributes() - 1);
        super.modelOptions = weka.core.Utils.splitOptions(modelOptions);

        knn.setKNN(Integer.parseInt(super.modelOptions[1]));
        knn.buildClassifier(super.trainSet);
    }

    @Override
    public void crossValidation(int k, int seed) throws Exception {
        super.testSet.setClassIndex(super.testSet.numAttributes() - 1);
        Random rd = new Random(seed);
        super.eval = new Evaluation(super.trainSet);
        super.eval.crossValidateModel(knn, super.testSet, k, rd);
    }

    @Override
    public void evalPerformance() throws Exception {
        super.testSet.setClassIndex(super.testSet.numAttributes() - 1);
        super.eval = new Evaluation(super.trainSet);
        eval.evaluateModel(knn, super.testSet);
    }

    @Override
    public Instances predict(Instances newDataset) throws Exception {
        newDataset.setClassIndex(newDataset.numAttributes() - 1);
        
        for (Instance instance: newDataset) {
            double predVal = knn.classifyInstance(instance);
            instance.setClassValue(predVal);
        }
        
        return newDataset;
    }
    
    @Override
    public String toString() {
        return knn.toString();
    }
}
