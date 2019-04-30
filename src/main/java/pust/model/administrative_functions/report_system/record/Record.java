package pust.model.administrative_functions.report_system.record;

import pust.model.administrative_functions.report_system.report.BaseReport;

import java.util.ArrayList;

public abstract class Record {

    private ArrayList<BaseReport> record;

    public Record(ArrayList<BaseReport> record) {
        this.record = record;
    }

    public abstract ArrayList<BaseReport> register(BaseReport report);

    public abstract ArrayList<BaseReport> view(BaseReport report);

    public abstract void delete(BaseReport report);

    public abstract ArrayList<BaseReport> edit(BaseReport report);

    public ArrayList<BaseReport> getRecord() {
        return record;
    }
}
