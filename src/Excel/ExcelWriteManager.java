package Excel;

import ConstantValues.Constants;
import ConstantValues.Sections;
import Util.FileUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.nio.file.Paths;


public abstract class ExcelWriteManager {
    XSSFWorkbook workbook;
    Sections section;
    boolean written;
    String fileName;

    protected ExcelWriteManager() {
        createBaseFolder();
    }

    private void createBaseFolder() {
        if (!FileUtil.createFolder(Paths.get(Constants.EXCEL_SAVE_PATH))) {
            System.out.println("Failed to create folder: " + Constants.EXCEL_SAVE_PATH);
        }
    }

    abstract void createFolder();
    public abstract void createExcelFile() ;

    public boolean isWritten() {
        return written;
    }
}
