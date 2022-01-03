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

    @Override
    public List<TeamModel> call() {
        List<TeamModel> teamData = new ArrayList<>();

        try {

            for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext(); ) {
                Row cells = iterator.next();
                TeamModel team = new TeamModel();

                // If first row is header, then skip the first row and start loading at 2nd row
                if (cells.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellItr = cells.cellIterator();

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
                        default: // member - String
                            team.addMember((String) getValueFromCell(cell));
                            break;
                    }
                }
                teamData.add(team);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return teamData;
    }

    private Object getValueFromCell(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            // case BOOLEAN -> cell.getBooleanCellValue();
            // case NUMERIC -> cell.getNumericCellValue(); // caution: return DOUBLE
            // case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            default -> null;
        };
    }

    public Sheet getSheet() {
        return sheet;
    }
}
