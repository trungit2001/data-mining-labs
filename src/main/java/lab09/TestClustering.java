/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab09;

/**
 *
 * @author ThanhTrungK15
 */
public class TestClustering {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String basePath = "D:\\Codes\\DataWeka\\arff\\";
        String dataset = basePath + "labor.arff";
        String newDataset = basePath + "labor_new.arff";
        int numberOfClutsers = 2;
        
        System.out.println("--- KMeans ---");
        KMeans kmeans = new KMeans();
        kmeans.loadData(kmeans.loadARFF(dataset));
        kmeans.fit(numberOfClutsers);
        
        System.out.println(kmeans.toString());
        kmeans.predict(kmeans.loadARFF(newDataset));

        System.out.println("--- Hierarchical Clusterer ---");
        AGNES agnes = new AGNES();
        agnes.loadData(agnes.loadARFF(dataset));
        agnes.fit(numberOfClutsers);
        
        System.out.println(agnes.toString());
        agnes.predict(agnes.loadARFF(newDataset));
    }
    
}
