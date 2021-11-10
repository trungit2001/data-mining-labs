/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import weka.associations.FPGrowth;

/**
 *
 * @author ThanhTrungK15
 */
public class FPGrowthModel extends Models {
    private final FPGrowth fpGrowth;
    
    /**
     * 
     * Constructor mặc định
     */
    public  FPGrowthModel() {
        fpGrowth = new FPGrowth();
    }
    
    /**
     * 
     * Constructor có tham số
     * 
     * @param pathFileToLoad Đường dẫn đến file dữ liệu *.arff cần tải lên
     * @throws Exception 
     */
    public FPGrowthModel(String pathFileToLoad) throws Exception {
        super.loadARFF(pathFileToLoad);
        fpGrowth = new FPGrowth();
    }
    
    /**
     * 
     * Khai thác luật kết hợp với thuật toán FP-Growth
     * 
     * @param modelOptions Chuỗi thông số tinh chỉnh cho mô hình FP-Growth
     * @throws Exception 
     */
    @Override
    public void mineRules(String modelOptions) throws Exception {
        super.modelOptions = weka.core.Utils.splitOptions(modelOptions);
        fpGrowth.setOptions(super.modelOptions);
        fpGrowth.buildAssociations(dataset);
    }

    /**
     * 
     * Xuất kết quả khai thác luật kết hợp theo FP-Growth ra file
     * 
     * @param pathFileToWriteResult Đường dẫn đến file lưu kết quả trên ổ đĩa
     * @throws IOException 
     */
    @Override
    public void saveResultMines(String pathFileToWriteResult) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFileToWriteResult))) {
            bw.write(toString());
        } catch (Exception e) {
            throw(e);
        }
    }
    
    /**
     * 
     * @return Các luật kết hợp tìm được với thuật toán FP-Growth
     */
    @Override
    public String toString() {
        return fpGrowth.toString();
    }
}
