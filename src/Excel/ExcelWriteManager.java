package Excel;

import ConstantValues.Sections;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;


public abstract class ExcelWriteManager {
    XSSFWorkbook workbook;
    Sections section;
    boolean written;
    String fileName;

    abstract void createExcelFile() ;
    abstract void setData(Collection T);
}
