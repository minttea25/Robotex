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


                while (cellItr.hasNext()) {
                    Cell cell = cellItr.next();

                    int columnIndex = cell.getColumnIndex();

                    switch (columnIndex) {
                        case 0: // no - pass this value
                            break;
                        case 1: // teamNumber - String
                            team.setTeamNumber((String) getValueFromCell(cell));
                            break;
                        case 2: // teamName - String
                            team.setTeamName((String) getValueFromCell(cell));
                            break;
                        case 3: // belong - String
                            team.setBelong((String) getValueFromCell(cell));
                            break;
                        case 4: // coach - String
                            team.setCoach((String) getValueFromCell(cell));
                            break;
                        case 5: // coach email - String
                            team.setCoachEmail((String) getValueFromCell(cell));
                            break;
                        case 6: // coach phone - String
                            team.setCoachPhone((String) getValueFromCell(cell));
                            break;
                        case 6 + 6:
                            // System.out.println("column end");
                            // over-number number
                            break;
                        default: // member - String
                            team.addMember((String) getValueFromCell(cell));
                            break;
                    }
                }
                teamData.add(team);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return teamData;
    }

    @Override
    public List<TeamModel> call() {
        List<TeamModel> teamData = new ArrayList<>();

        boolean eofFlag = false;

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

                while (cellItr.hasNext()) {
                    Cell cell = cellItr.next();
                    int columnIndex = cell.getColumnIndex();

                    switch (columnIndex) {
                        case 0: // no - pass this value
                            if (getValueFromCell(cell) == null) {
                                eofFlag = true;
                                System.out.println("NULSFLDSJFLSFS");
                            }
                            break;
                        case 1: // teamNumber - String
                            team.setTeamNumber((String) getValueFromCell(cell));
                            break;
                        case 2: // teamName - String
                            team.setTeamName((String) getValueFromCell(cell));
                            break;
                        case 3: // belong - String
                            team.setBelong((String) getValueFromCell(cell));
                            break;
                        case 4: // coach - String
                            team.setCoach((String) getValueFromCell(cell));
                            break;
                        case 5: // coach email - String
                            team.setCoachEmail((String) getValueFromCell(cell));
                            break;
                        case 6: // coach phone - String
                            team.setCoachPhone((String) getValueFromCell(cell));
                            break;
                        case 6 + 6:
                            System.out.println("column end");
                            break;
                        default: // member - String
                            team.addMember((String) getValueFromCell(cell));
                            break;
                    }
                    if (eofFlag) {
                        break;
                    }
                }
                if (!eofFlag) {
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
                return cell.getNumericCellValue(); // caution: return DOUBLE
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
