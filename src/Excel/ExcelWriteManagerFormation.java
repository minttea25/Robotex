package Excel;

import ConstantValues.Constants;
import ConstantValues.Sections;
import Model.TeamModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ExcelWriteManagerFormation extends ExcelWriteManager{
    Map<String, List<TeamModel>> data;

    public ExcelWriteManagerFormation(Sections section, Map<String, List<TeamModel>> data, String fileName) {
        this.section = section;
        this.data = data;
        this.fileName = fileName;
    }

    @Override
    void createExcelFile() {
        if (data.size() == 0) {
            System.out.println("There is no data to write.");
            return;
        }

        FileOutputStream fos = null;

        try {
            int row = 0;
            int teamStartColumn = 1;

            fos = new FileOutputStream(fileName);
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

            for (String entryNumber : data.keySet()) {
                curRow = sheet.createRow(row);
                curRow.createCell(0).setCellValue(entryNumber);

                for (var team : data.get(entryNumber)) {
                    for (int i = teamStartColumn; i < TeamModel.NUMBERS_OF_ATTRIBUTES + teamStartColumn; i++) {
                        int j = i - TeamModel.NUMBERS_OF_ATTRIBUTES - 1;
                        switch (i - 1) {
                            case 0: // teamNumber - String
                                curRow.createCell(i).setCellValue(team.getTeamNumber());
                                break;
                            case 1: // teamName
                                curRow.createCell(i).setCellValue(team.getTeamName());
                                break;
                            case 2: // belong
                                curRow.createCell(i).setCellValue(team.getBelong());
                                break;
                            case 3: // coach
                                curRow.createCell(i).setCellValue(team.getCoach());
                                break;
                            case 4: // coach email
                                curRow.createCell(i).setCellValue(team.getCoachEmail());
                                break;
                            case 5: // coach phone
                                curRow.createCell(i).setCellValue(team.getCoachPhone());
                                break;
                            default: // members
                                curRow.createCell(i).setCellValue(team.getMembers().get(j));
                                break;
                        }
                    }
                    row++;
                    curRow = sheet.createRow(row);
                }
            }

            workbook.write(fos);
            System.out.println("File created");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void setData(Collection T) {
        try {
            this.data = (Map<String, List<TeamModel>>) T;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
