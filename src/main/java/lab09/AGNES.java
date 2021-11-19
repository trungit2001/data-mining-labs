/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab09;

import weka.clusterers.HierarchicalClusterer;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author ThanhTrungK15
 */
public class AGNES extends Clustering {
    HierarchicalClusterer agnes;
    
    public AGNES() {
        agnes = new HierarchicalClusterer();
    }
    
    @Override
    public void fit(int k) throws Exception {
        agnes.setNumClusters(k);
        agnes.buildClusterer(dataset);
    }

    @Override
    public void predict(Instances newDataset) throws Exception {
        for (Instance instance: newDataset) {
            int predVal = agnes.clusterInstance(instance);
            System.out.println(String.format("Instace %s belongs to cluster %s", instance.toString(), predVal));
        }
    }
    
    @Override
    public String toString() {
        return agnes.toString();
    }
}
