/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab06;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Debug.Random;
import weka.core.SerializationHelper;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author ThanhTrungK15
 */
public class DecisionTreeClassifier extends Classification {
    J48 decisionTree;
    
    public DecisionTreeClassifier() throws Exception {
        decisionTree = new J48();
    }
    
    @Override
    public void fit(String modelOptions) throws Exception {
        super.trainSet.setClassIndex(super.trainSet.numAttributes() - 1);
        super.modelOptions = weka.core.Utils.splitOptions(modelOptions);
        decisionTree.setOptions(super.modelOptions);
        decisionTree.buildClassifier(super.trainSet);
    }

    @Override
    public void crossValidation(int k, int seed) throws Exception {
        super.testSet.setClassIndex(super.testSet.numAttributes() - 1);
        Random rd = new Random(seed);
        super.eval = new Evaluation(super.trainSet);
        super.eval.crossValidateModel(decisionTree, super.testSet, k, rd);
    }

    @Override
    public void evalPerformance() throws Exception {
        super.testSet.setClassIndex(super.testSet.numAttributes() - 1);
        super.eval = new Evaluation(super.trainSet);
        eval.evaluateModel(decisionTree, super.testSet);
    }

    @Override
    public Instances predict(Instances newDataset) throws Exception {
        newDataset.setClassIndex(newDataset.numAttributes() - 1);
        
        for (Instance instance: newDataset) {
            double predVal = decisionTree.classifyInstance(instance);
            instance.setClassValue(predVal);
        }
        
        return newDataset;
    }
    
    public void loadModel(String pathFileToLoad) throws Exception {
        decisionTree = (J48) SerializationHelper.read(pathFileToLoad);
    }
    
    public void saveModel(String pathFileToSave) throws Exception {
        SerializationHelper.write(pathFileToSave, decisionTree);
    }
    
    @Override
    public String toString() {
        return decisionTree.toString();
    }
}
