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
import java.util.concurrent.Callable;

public class ExcelWriteManagerTicket extends ExcelWriteManager implements Callable {
    List<TeamModel> data;
    int numberOfTickets;

    public ExcelWriteManagerTicket(Sections section, List<TeamModel> data, String fileName, int numberOfTickets) {
        super();

        this.section = section;
        this.data = data;
        this.fileName = "." + File.separator + Constants.EXCEL_SAVE_PATH_TICKET + File.separator + fileName;
        this.numberOfTickets = numberOfTickets;

        written = false;

        createFolder();
    }

    @Override
    void createFolder() {
        if (!FileUtil.createFolder(Paths.get(Constants.EXCEL_SAVE_PATH_TICKET))) {
            //System.out.println("Failed to create folder: " + Constants.EXCEL_SAVE_PATH_TICKET);
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

            fos = new FileOutputStream(fName);
            workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet(section.toString());
            XSSFRow curRow;

            // write column name on top of the sheet
            curRow = sheet.createRow(row);
            curRow.createCell(0).setCellValue(Constants.TEAM_NUMBER);
            curRow.createCell(1).setCellValue(Constants.TEAM_NAME);
            curRow.createCell(2).setCellValue(Constants.BELONG);
            curRow.createCell(3).setCellValue(Constants.COACH);
            curRow.createCell(4).setCellValue(Constants.COACH_EMAIL);
            curRow.createCell(5).setCellValue(Constants.COACH_PHONE);
            curRow.createCell(6).setCellValue(Constants.MEMBER1);
            curRow.createCell(7).setCellValue(Constants.MEMBER2);
            curRow.createCell(8).setCellValue(Constants.MEMBER3);
            curRow.createCell(9).setCellValue(Constants.MEMBER4);
            curRow.createCell(10).setCellValue(Constants.MEMBER5);

            row++;

            for (TeamModel team : data) {
                curRow = sheet.createRow(row);

                for (int i=0; i<TeamModel.NUMBERS_OF_ATTRIBUTES + team.getMembers().size() - 1; i++) {

                    if (row <= numberOfTickets) {
                        curRow.createCell(0).setCellValue(Constants.WORLD_TICKET_TEAM_TAG);
                    }
                    else {
                        curRow.createCell(0).setCellValue(Constants.WORLD_TICKET_TEAM_PRE_TAG + (row-numberOfTickets));
                    }

                    int j = i - TeamModel.NUMBERS_OF_ATTRIBUTES + 1; // column 1 부터 출력

                    switch (i) {
                        case 0 : // teamNumber - String
                            //curRow.createCell(i+1).setCellValue(team.getTeamNumber());
                            setCellValue(curRow, i+1, team.getTeamNumber());
                            break;
                        case 1 : // teamName
                            //curRow.createCell(i+1).setCellValue(team.getTeamName());
                            setCellValue(curRow, i+1, team.getTeamName());
                            break;
                        case 2 : // belong
                            //curRow.createCell(i+1).setCellValue(team.getBelong());
                            setCellValue(curRow, i+1, team.getBelong());
                            break;
                        case 3 : // coach
                            //curRow.createCell(i+1).setCellValue(team.getCoach());
                            setCellValue(curRow, i+1, team.getCoach());
                            break;
                        case 4 : // coach email
                            //curRow.createCell(i+1).setCellValue(team.getCoachEmail());
                            setCellValue(curRow, i+1, team.getCoachEmail());
                            break;
                        case 5 : // coach phone
                            //curRow.createCell(i+1).setCellValue(team.getCoachPhone());
                            setCellValue(curRow, i+1, team.getCoachPhone());
                            break;
                        default : // members
                            //curRow.createCell(i+1).setCellValue(team.getMembers().get(j));
                            setCellValue(curRow, i+1, team.getMembers().get(j));
                            break;
                    }
                }

                /*if (row == numberOfTickets || row == (1 + numberOfTickets * 3)) {
                    row++;
                    curRow = sheet.createRow(row);
                    curRow.createCell(0).setCellValue("");
                }*/
                row++;
            }

            workbook.write(fos);
            //System.out.println("File created");
            written = true;
        }  catch (Exception e) {
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

    public boolean setData(List<TeamModel> T) {
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
        return written;
    }
}
