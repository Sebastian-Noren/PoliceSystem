package pust.model.administrative_functions.report_system.record;

import java.util.ArrayList;

public abstract class Record<T> {

    private ArrayList<T> record;

    public Record(ArrayList<T> record) {
        this.record = record;
    }

    public abstract void register(T report);

    public abstract ArrayList<T> view (T report);

    public abstract void delete (T report);

    public abstract ArrayList<T> edit (T report);
}
