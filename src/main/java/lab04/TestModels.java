/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab04;

/**
 *
 * @author ThanhTrungK15
 */
public class TestModels {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            /* Đường dẫn đến file dữ liệu cần mở, nhớ đặt 2 dấu "\" */
            String pathFileToLoad = "D:\\Codes\\DataWeka\\arff\\supermarket.arff";

           /* Đọc file *.csv */
//           String pathFileToLoad = "D:\\Codes\\DataWeka\\csv\\apriori.csv";

           /* Lưu vào đường dẫn này */
//           String pathFileToWrite = "D:\\Codes\\DataWeka\\arff\\apriori_preprocessed.arff";

            /* Đường dẫn để lưu file kết quả */
            String pathFileToWriteResult = "D:\\Codes\\DataWeka\\results\\supermarket_result.txt";
            
           /* Các tham số tinh chỉnh cho mô hình Apriori và FP-Growth */
//            String arModelCfg = "-N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1 -S -1.0 -c -1";
            String fpModelCfg = "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.1";
            
            /* Các tham số tinh chỉnh cho các bộ lọc */
//            String argsRemoveFilterCfg = "-R 1";
            String argsRemoveByNameFilterCfg = "-E department[0-9]+";
            String argsReplaceMissingFilterCfg = "-A first-last -N f -R 0 -F \"yyyy-MM-dd\\'T\\'HH:mm:ss\"";
            String argsNominalToBinaryFilterCfg = "-N -R first-last";

           /* Bài 1 */
//            AprioriModel arModel = new AprioriModel(pathFileToLoad);
//            arModel.removeByName(argsRemoveByNameFilterCfg);
//            arModel.mineRules(arModelCfg);
//            arModel.saveARFF(pathFileToWriteResult);
//            
//            System.out.println(arModel.toString());
                 
           /* Bài 2 */
            FPGrowthModel fpModel = new FPGrowthModel(pathFileToLoad);
            fpModel.removeByName(argsRemoveByNameFilterCfg);
            fpModel.replaceMissingWithUserConstant(argsReplaceMissingFilterCfg);
            fpModel.convertNominalToBinary(argsNominalToBinaryFilterCfg);
            fpModel.mineRules(fpModelCfg);
            fpModel.saveResultMines(pathFileToWriteResult);
            
            System.out.println(fpModel.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        String basePath = "D:\\Codes\\DataWeka\\arff\\";
//        String mushTrain = basePath + "mushroom_train.arff";
//        String mushTest = basePath + "mushroom_test.arff";
//        String mushNew = basePath + "mushroom_new.arff";
//        String mushPredicted = basePath + "mushroom_predicted.arff";
//        
//        NaiveBayesClassifier nbModel = new NaiveBayesClassifier();
//        
//        Instances trainSet = nbModel.loadARFF(mushTrain);
//        Instances testSet = nbModel.loadARFF(mushTest);
//        Instances newSet = nbModel.loadARFF(mushNew);
//        
//        nbModel.buildNBModel(trainSet);
//        nbModel.evalModel(testSet, 10);
//        nbModel.predict(newSet, mushPredicted);
        
//        System.out.println(nbModel.printSummary());
//        System.out.println(nbModel.printEvalResult());
    }
}
