package Setup;

import ConstantValues.Constants;
import ConstantValues.ErrorMsg;
import ConstantValues.GUIValue;
import ConstantValues.Sections;
import Excel.ExcelReadManager;
import Excel.ExcelWriteManagerFormation;
import Excel.ExcelWriteManagerTicket;
import Model.ProgramFunctions;
import Model.SetupDataModel;
import Model.TeamModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Setup {
    String excelFilePath;
    String setupFilePath;

    SetupDataModel setupDataModel;
    Map<Sections, List<TeamModel>> teamData;
    Map<Sections, Map<Integer, List<TeamModel>>> entryMaps = new HashMap<>();
    Map<Sections, Boolean> status = new HashMap<>();
    Map<Sections, Boolean> writeStatus = new HashMap<>();

    ExcelReadManager erm;

    boolean setupFileLoaded;
    boolean excelFileLoaded;

    Gson gson = new Gson();

    String checkNumErrorList = "";

    public Setup(ProgramFunctions fun) {
        if (fun == ProgramFunctions.Formation) {
            setupFilePath = Constants.FORMATION_SETUP_FILE_PATH;
        }
        else if (fun == ProgramFunctions.Ticket) {
            setupFilePath = Constants.TICKET_SETUP_FILE_PATH;
        }

        setupFileLoaded = false;
        excelFileLoaded = false;

        excelFilePath = "";
    }

    public void loadSetupFile() {
        File file = new File(setupFilePath);

        if (!file.exists()) {
            //System.out.println("There is no such file: " + file.getPath());
            return;
        }

        try {
            JsonReader jSonReader = new JsonReader(new FileReader(file));
            setupDataModel = gson.fromJson(jSonReader, SetupDataModel.class);

            for(var s : Sections.values()) {
                if (setupDataModel.getValueBySection(s) <= 0) {
                    setupFileLoaded = false;
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setupFileLoaded = true;
    }

    public void writeSetupFile() {
        FileWriter fw = null;

        try {
            fw = new FileWriter(setupFilePath);
            gson.toJson(setupDataModel, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadExcelFile() {
        erm = new ExcelReadManager(excelFilePath);

        erm.loadFile();

        if (erm.getData() == null || erm.getData().size() == 0) {
            //System.out.println("Reading excel file failed");
            excelFileLoaded = false;
        }
        else {
            teamData = erm.getData();
            excelFileLoaded = true;
        }
    }

    // after clicking save btn, it must be called after 'load excel'
    public boolean checkSetupValues() {
        boolean flag = true;
        for (Sections s : teamData.keySet()) {
            int num = switch (s) {
                case LegoSumo1kg -> setupDataModel.getLegoSumo1kg();
                case LegoSumo3kg -> setupDataModel.getLegoSumo3kg();
                case LineFollowingE -> setupDataModel.getLineFollowingE();
                case LineFollowingJH -> setupDataModel.getLineFollowingJH();
                case LegoFolkraceE -> setupDataModel.getLegoFolkraceE();
                case LegoFolkraceJH -> setupDataModel.getLegoFolkraceJH();
            };

            if(teamData.get(s).size() < num) {
                checkNumErrorList += "\n" + s + ": " + teamData.get(s).size();
                status.put(s, false);
                flag = false;
            }
            else {
                status.put(s, true);
            }
        }

        return flag;
    }

    public void saveFiles(ProgramFunctions fun) {
        shuffleData();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.SAVE_FILE_DATE_FORMAT);

        ExecutorService service = Executors.newFixedThreadPool(status.size());
        Future<Boolean>[] futures = new Future[status.size()];

        // 로드 된 data 에 대해서만 수행
       if (fun == ProgramFunctions.Formation) {
            // make entreis
            for (Sections s : teamData.keySet()) {
                if (!status.get(s)) {
                    continue;
                }
                entryMaps.put(s, new HashMap<>());
                int num = setupDataModel.getValueBySection(s);
                for (int i=0; i<num; i++) {
                    entryMaps.get(s).put(i, new ArrayList<>());
                }

                int i = 0;
                for (var team : teamData.get(s)) {
                    entryMaps.get(s).get(i).add(team);
                    if (i >= entryMaps.get(s).size() - 1) {
                        i = 0;
                        continue;
                    }
                    i++;
                }
            }

            ExcelWriteManagerFormation[] ewms = new ExcelWriteManagerFormation[status.size()];

            try {
                int idx=0;
                for (Sections s : status.keySet()) {
                    if (!status.get(s)) {
                        continue;
                    }
                    String fileName = s.toString() + " - " + date + " " + time.format(timeFormatter);
                    ewms[idx] = new ExcelWriteManagerFormation(
                            s,
                            entryMaps.get(s),
                            fileName
                    );
                    futures[idx] = service.submit(ewms[idx]);
                    idx++;
                }
                idx = 0;
                for (Sections s : status.keySet()) {
                    if (!status.get(s)) {
                        continue;
                    }
                    if (!futures[idx].get()) {
                        writeStatus.put(s, false);
                    }
                    else {
                        writeStatus.put(s, true);
                    }
                    idx++;
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                service.shutdown();
            }
       }
       // ticket
       else {
           ExcelWriteManagerTicket[] ewms = new ExcelWriteManagerTicket[status.size()];

           try {
               int idx=0;
               for (Sections s : status.keySet()) {
                   if (!status.get(s)) {
                       continue;
                   }
                   String fileName = s.toString() + " - " + date + " " + time.format(timeFormatter);
                   int num = setupDataModel.getValueBySection(s);
                   ewms[idx] = new ExcelWriteManagerTicket(
                           s,
                           teamData.get(s),
                           fileName,
                           num
                   );
                   futures[idx] = service.submit(ewms[idx]);
                   idx++;
               }
               idx = 0;
               for (Sections s : status.keySet()) {
                   if (!status.get(s)) {
                       continue;
                   }
                   if (!futures[idx].get()) {
                       writeStatus.put(s, false);
                   }
                   else {
                       writeStatus.put(s, true);
                   }
                   idx++;
               }
           } catch (ExecutionException | InterruptedException e) {
               e.printStackTrace();
           } finally {
               service.shutdown();
           }
       }
    }

    private void shuffleData() {
        for(Sections s : teamData.keySet()) {
            Collections.shuffle(teamData.get(s));
        }
        for(Sections s : teamData.keySet()) {
            Collections.shuffle(teamData.get(s));
        }
    }

    public boolean isSetupFileLoaded() {
        return setupFileLoaded;
    }

    public boolean isExcelFileLoaded() {
        return excelFileLoaded;
    }

    public void setExcelFilePath(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public List<TeamModel> getTeamDataBySection(Sections section) {
        List<TeamModel> t = teamData.get(section);

        if (t == null ) {
            return null;
        }
        else {
            return t;
        }
    }

    public SetupDataModel getSetupDataModel() {
        return setupDataModel;
    }

    public String getSetupFilePath() {
        return setupFilePath;
    }

    public String getExcelFilePath() {
        return excelFilePath;
    }

    public ExcelReadManager getExcelReadManager() {
        return erm;
    }

    public String getCheckNumErrorList() {
        return checkNumErrorList;
    }

    public Map<Sections, Boolean> getWriteStatus() {
        return writeStatus;
    }

    public Map<Sections, Map<Integer, List<TeamModel>>> getEntryMaps() {
        return entryMaps;
    }

    public Map<Sections, Boolean> getStatus() {
        return status;
    }
}
