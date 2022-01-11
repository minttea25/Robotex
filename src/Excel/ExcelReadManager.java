package Excel;

import ConstantValues.Sections;
import Model.TeamModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExcelReadManager {
    String filePath;
    Map<Sections, List<TeamModel>> data = new HashMap<>();
    Map<Sections, Boolean> dataLoaded = new HashMap<>();

    boolean fullFileLoaded;

    Workbook workbook;
    InputStream is;

    public ExcelReadManager() {
        this.fullFileLoaded = false;
    }

    public ExcelReadManager(String filePath) {
        this();

        // check the file exist
        if (!(new File(filePath).exists())) {
            //System.out.println("There is no file: " + filePath);
            return;
        }
        this.filePath = filePath;
    }

    public boolean setFilePath(String filePath) {
        // check the file exist
        if (!(new File(filePath).exists())) {
            //System.out.println("There is no file: " + filePath);
            return false;
        }
        this.filePath = filePath;
        return true;
    }

    public void loadFile() {
        try {
            is = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(is);

            /*if (Sections.values().length != workbook.getNumberOfSheets()) {
                System.out.println("Number of sheets != Sections.length");
                return;
            }*/

            loadAllSheet();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<TeamModel> loadSheet(Sections section) {
        List<TeamModel> list = null;

        try {
            is = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(is);

            /*if (Sections.values().length != workbook.getNumberOfSheets()) {
                System.out.println("Number of sheets != Sections.length");
                return null;
            }*/

            for (int i=0; i<workbook.getNumberOfSheets(); i++) {
                String sheetName = workbook.getSheetAt(i).getSheetName();

                try {
                    if (section == Sections.valueOf(sheetName)) {
                        SheetReadManager s = new SheetReadManager(workbook.getSheetAt(i));
                        list = s.singleCall();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private void loadAllSheet() {
        fullFileLoaded  = true;

        // check availability sheet name
        int availableSheetNum = 0;
        List<Integer> availableIndex = new ArrayList<>();
        for (int i=0; i<workbook.getNumberOfSheets(); i++) {
            String sheetName = workbook.getSheetAt(i).getSheetName();
            try {
                Sections.valueOf(sheetName);
                availableIndex.add(i);
                availableSheetNum++;
            } catch (IllegalArgumentException e) {
                //System.out.println("wrong sheet name: " + sheetName);
                fullFileLoaded = false;
            }
        }

        ExecutorService service = Executors.newFixedThreadPool(availableSheetNum);

        SheetReadManager[] srms = new SheetReadManager[availableSheetNum];

        Future<List<TeamModel>>[] futures = new Future[availableSheetNum];

        try {
            int i=0;
            for (int index : availableIndex) {
                srms[i] = new SheetReadManager(workbook.getSheetAt(index));
                futures[i] = service.submit(srms[i]);

                i++;
            }

            for (i=0; i<availableSheetNum; i++) {
                List<TeamModel> t = futures[i].get();
                if (t == null) {
                    dataLoaded.put(Sections.valueOf(srms[i].getSheet().getSheetName()), false);

                    //System.out.println("Sheet loading failed at Sheet: " + srms[i].getSheet().getSheetName());
                }
                else {
                    data.put(Sections.valueOf(srms[i].getSheet().getSheetName()), t);
                    dataLoaded.put(Sections.valueOf(srms[i].getSheet().getSheetName()), true);
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

        /*if (fullFileLoaded) {
            System.out.println("All sheet loaded completely");
        }
        else {
            System.out.println("Some sheet is not loaded: " + availableSheetNum + " in " + workbook.getNumberOfSheets());
        }*/
    }

    public Map<Sections, List<TeamModel>> getData() {
        return data;
    }

    public List<TeamModel> getData(Sections section) {
        try {
            return data.get(section);
        } catch (NullPointerException e) {
            //System.out.println("There is no loaded sheet name: " + section);
            return null;
        }
    }

    public boolean isFullFileLoaded() {
        return fullFileLoaded;
    }

    public Map<Sections, Boolean> getDataLoaded() {
        return dataLoaded;
    }

    public boolean getDataLoaded(Sections section) {
        try {
            return dataLoaded.get(section);
        } catch (NullPointerException e) {
            // System.out.println("There is no loaded sheet name: " + section);
            return false;
        }
    }
}
