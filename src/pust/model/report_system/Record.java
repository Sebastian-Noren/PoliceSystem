package pust.model.report_system;

import java.util.ArrayList;
import java.util.List;

public abstract class Record<T> {

    private ArrayList<T> record;

    public Record(ArrayList<T> record) {
        this.record = record;
    }

    public abstract void register(T report);

    public abstract ArrayList<T> view(T report);

    public abstract void delete(T report);

    public abstract ArrayList<T> edit(T report);

    public ArrayList<T> getRecord() {
        return record;
    }
}
