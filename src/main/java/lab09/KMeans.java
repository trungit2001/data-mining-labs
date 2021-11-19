/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab09;

import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author admin
 */
public class KMeans extends Clustering {
    SimpleKMeans kmeans;
    
    public KMeans() {
        kmeans = new SimpleKMeans();
    }
    
    @Override
    public void fit(int k) throws Exception {
        kmeans.setNumClusters(k);
        kmeans.buildClusterer(dataset);
    }

    @Override
    public void predict(Instances newDataset) throws Exception {
        for (Instance instance: newDataset) {
            int predVal = kmeans.clusterInstance(instance);
            System.out.println(String.format("Instace %s belongs to cluster %s", instance.toString(), predVal));
        }
    }

    @Override
    public String toString() {
        return kmeans.toString();
    }
}
    