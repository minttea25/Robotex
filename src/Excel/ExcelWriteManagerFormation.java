package Excel;

import ConstantValues.Constants;
import ConstantValues.Sections;
import Model.TeamModel;
import Util.FileUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExcelWriteManagerFormation extends ExcelWriteManager implements Callable {
    Map<Integer, List<TeamModel>> data;

    public ExcelWriteManagerFormation(Sections section, Map<Integer, List<TeamModel>> data, String fileName) {
        super();

        this.section = section;
        this.data = data;
        this.fileName = "." + File.separator + Constants.EXCEL_SAVE_PATH_FORMATION + File.separator + fileName;

        written = false;

        createFolder();
    }


    @Override
    void createFolder() {
        if (!FileUtil.createFolder(Paths.get(Constants.EXCEL_SAVE_PATH_FORMATION))) {
            //System.out.println("Failed to create folder: " + Constants.EXCEL_SAVE_PATH_FORMATION);
        }
    }

    @Override
    public void createExcelFile() {
        if (data == null || data.size() == 0) {
            //System.out.println("There is no data to write.");
            return;
        }

        FileOutputStream fos = null;
        String fName = fileName  + "."+ Constants.EXCEL_EXTENSION_XLSX;

        try {
            int row = 0;
            int teamStartColumn = 1;

            fos = new FileOutputStream(fName);
            workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet(section.toString());
            XSSFRow curRow;

            // write column name on top of the sheet
            curRow = sheet.createRow(row);
            curRow.createCell(0).setCellValue(Constants.ENTRY);
            curRow.createCell(1).setCellValue(Constants.TEAM_NUMBER);
            curRow.createCell(2).setCellValue(Constants.TEAM_NAME);
            curRow.createCell(3).setCellValue(Constants.BELONG);
            curRow.createCell(4).setCellValue(Constants.COACH);
            curRow.createCell(5).setCellValue(Constants.COACH_EMAIL);
            curRow.createCell(6).setCellValue(Constants.COACH_PHONE);
            curRow.createCell(7).setCellValue(Constants.MEMBER1);
            curRow.createCell(8).setCellValue(Constants.MEMBER2);
            curRow.createCell(9).setCellValue(Constants.MEMBER3);
            curRow.createCell(10).setCellValue(Constants.MEMBER4);
            curRow.createCell(11).setCellValue(Constants.MEMBER5);

            row++;

            for (int entryNumber : data.keySet()) {
                curRow = sheet.createRow(row);
                curRow.createCell(0).setCellValue(String.valueOf(entryNumber));

                for (var team : data.get(entryNumber)) {
                    for (int i = teamStartColumn; i < TeamModel.NUMBERS_OF_ATTRIBUTES + team.getMembers().size() + teamStartColumn - 1; i++) {
                        int j = i - TeamModel.NUMBERS_OF_ATTRIBUTES;
                        switch (i - 1) {
                            case 0 -> // teamNumber - String
                                    curRow.createCell(i).setCellValue(team.getTeamNumber());
                            case 1 -> // teamName
                                    curRow.createCell(i).setCellValue(team.getTeamName());
                            case 2 -> // belong
                                    curRow.createCell(i).setCellValue(team.getBelong());
                            case 3 -> // coach
                                    curRow.createCell(i).setCellValue(team.getCoach());
                            case 4 -> // coach email
                                    curRow.createCell(i).setCellValue(team.getCoachEmail());
                            case 5 -> // coach phone
                                    curRow.createCell(i).setCellValue(team.getCoachPhone());
                            default -> // members
                                    curRow.createCell(i).setCellValue(team.getMembers().get(j));
                        }
                    }
                    row++;
                    curRow = sheet.createRow(row);
                }
            }

            workbook.write(fos);
            //System.out.println("File created");
            written = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!written) {
                if (FileUtil.deleteFile(Paths.get(fName))) {
                    //System.out.println("deleted file");
                }
            }
        }
    }

    public boolean setData(Map<Integer, List<TeamModel>> T) {
        try {
            this.data = T;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean call() {
        createExcelFile();
        return this.written;
    }
}
