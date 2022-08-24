package Excel;

import Model.TeamModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class SheetReadManager implements Callable {
    Sheet sheet;

    SheetReadManager(Sheet sheet) {
        this.sheet = sheet;
    }

    public List<TeamModel> singleCall() {
        List<TeamModel> teamData = new ArrayList<>();

        try {
            Iterator<Row> rowItr = sheet.iterator();

            while (rowItr.hasNext()) {

                TeamModel team = new TeamModel();

                Row row = rowItr.next();

                // If first row is header, then skip the first row and start loading at 2nd row
                if (row.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellItr = row.cellIterator();
                boolean valid = false;

                while (cellItr.hasNext()) {
                    Cell cell = cellItr.next();

                    int columnIndex = cell.getColumnIndex();

                    if (columnIndex == 0) {
                        valid = true;
                    }

                    switch (columnIndex) {
                        case 0: // no - pass this value
                            // 빈 칸 데이터가 있을 경우도 데이터가 없는 것으로 간주해야함
                            Object o = getValueFromCell(cell);
                            if (o == null || o.toString().trim().equals("")) {
                                valid = false;
                            }
                            break;
                        case 1: // teamNumber - String
                            team.setTeamNumber(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 2: // teamName - String
                            team.setTeamName(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 3: // belong - String
                            team.setBelong(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 4: // coach - String
                            team.setCoach(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 5: // coach email - String
                            team.setCoachEmail(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 6: // coach phone - String
                            team.setCoachPhone(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 6 + 6:
                            // System.out.println("column end");
                            // over-number number
                            break;
                        default: // member - String
                            team.addMember(String.valueOf(getValueFromCell(cell)));
                            break;
                    }
                }
                if (valid)
                    teamData.add(team);
                else
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return teamData;
    }

    @Override
    public List<TeamModel> call() {
        List<TeamModel> teamData = new ArrayList<>();

        try {
            Iterator<Row> rowItr = sheet.iterator();

            while (rowItr.hasNext()) {
                TeamModel team = new TeamModel();

                Row row = rowItr.next();

                // If first row is header, then skip the first row and start loading at 2nd row
                if (row.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellItr = row.cellIterator();
                boolean valid = false;
                while (cellItr.hasNext()) {
                    Cell cell = cellItr.next();
                    int columnIndex = cell.getColumnIndex();

                    // No 데이터가 존재하는지 확인
                    // 없다면 해당 라인이 마지막 라인으로 인식
                    if (columnIndex == 0) {
                        valid = true;
                    }

                    switch (columnIndex) {
                        case 0: // no - pass this value
                            // 빈 칸 데이터가 있을 경우도 데이터가 없는 것으로 간주해야함
                            Object o = getValueFromCell(cell);
                            if (o == null || o.toString().trim().equals("")) {
                                valid = false;
                            }
                            break;
                        case 1: // teamNumber - String
                            team.setTeamNumber(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 2: // teamName - String
                            team.setTeamName(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 3: // belong - String
                            team.setBelong(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 4: // coach - String
                            team.setCoach(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 5: // coach email - String
                            team.setCoachEmail(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 6: // coach phone - String
                            team.setCoachPhone(String.valueOf(getValueFromCell(cell)));
                            break;
                        case 6 + 6:
                            System.out.println("column end");
                            break;
                        default: // member - String
                            team.addMember(String.valueOf(getValueFromCell(cell)));
                            break;
                    }
                    // invalid 한 라인일 경우 데이터의 끝으로 간주하여 읽기 stop
                    if (!valid) {
                        break;
                    }
                }
                if (valid) {
                    teamData.add(team);
                }
                else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return teamData;
    }

    private Object getValueFromCell(Cell cell) {
        switch ((cell.getCellType())) {
            case STRING:
                return cell.getStringCellValue();
//            case BOOLEAN :
//                return cell.getBooleanCellValue();
            case NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue()); // caution: return DOUBLE -> String
//            case FORMULA:
//                return cell.getCellFormula();
            case BLANK :
                return null;
            default:
                return null;

        }

        /*return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            // case BOOLEAN -> cell.getBooleanCellValue();
            case NUMERIC -> cell.getNumericCellValue(); // caution: return DOUBLE
            // case FORMULA -> cell.getCellFormula();
            case BLANK -> null;
            default -> null;
        };*/
    }

    public Sheet getSheet() {
        return sheet;
    }
}
