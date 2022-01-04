package Setup;

import ConstantValues.Constants;
import ConstantValues.Sections;
import Excel.ExcelReadManager;
import Model.ProgramFunctions;
import Model.SetupDataModel;
import Model.TeamModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Setup {
    String excelFilePath;
    String setupFilePath;

    SetupDataModel setupDataModel;
    Map<Sections, List<TeamModel>> teamData;

    ExcelReadManager erm;

    boolean setupFileLoaded;
    boolean excelFileLoaded;

    Gson gson = new Gson();

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
            System.out.println("There is no such file: " + file.getPath());
            return;
        }

        try {
            JsonReader jSonReader = new JsonReader(new FileReader(file));
            setupDataModel = gson.fromJson(jSonReader, SetupDataModel.class);
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
            System.out.println("Reading excel file failed");
            excelFileLoaded = false;
        }
        else {
            teamData = erm.getData();
            excelFileLoaded = true;
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

    public SetupDataModel getSetupDataModel() {
        return setupDataModel;
    }

    public String getSetupFilePath() {
        return setupFilePath;
    }

    public String getExcelFilePath() {
        return excelFilePath;
    }
}
