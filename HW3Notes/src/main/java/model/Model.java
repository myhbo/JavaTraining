package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private final List<Map<String, String>> notes = new ArrayList<>();

    public void addNotes(HashMap<String, String> newNote) {
        this.notes.add(newNote);
    }

    public List<Map<String, String>> getNotes() {
        return notes;
    }
}
