package pust.model.administrative_functions.report_system.record;

import pust.model.administrative_functions.report_system.report.BaseReport;

import java.util.ArrayList;

public class ReportRecord extends Record {

    public ReportRecord(ArrayList<BaseReport> record) {
        super(record);
    }

    @Override
    public ArrayList<BaseReport> register(BaseReport report) {
        return null;
    }

    @Override
    public ArrayList<BaseReport> view(BaseReport report) {
        return null;
    }

    @Override
    public void delete(BaseReport report) {

    }

    @Override
    public ArrayList<BaseReport> edit(BaseReport report) {
        return null;
    }
}
