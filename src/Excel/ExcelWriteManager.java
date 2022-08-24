package Excel;

import ConstantValues.Constants;
import ConstantValues.Sections;
import Util.FileUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
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

    public void setCellValue(XSSFRow curRow, int columnIndex, String content) {
        try {
            // 기존 null 값을 Object로 캐스팅 하고 다시 String으로 캐스팅 하니까 "null" 이 되어 버렸음......................
            if (content ==  null || content.equals("null")) {
                curRow.createCell(columnIndex).setCellValue("");
            }
            else {
                curRow.createCell(columnIndex).setCellValue(content);
            }

        }
        catch (Exception e) {}
    }

    public void setCellValue(XSSFRow curRow, int columnIndex, int content) {
        try {
            curRow.createCell(columnIndex).setCellValue(content);
        }
        catch (Exception e) {}
    }

    abstract void createFolder();
    public abstract void createExcelFile() ;

    public boolean isWritten() {
        return written;
    }
}
