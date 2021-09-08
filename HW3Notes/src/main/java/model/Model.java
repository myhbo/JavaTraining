package model;

import controller.LoginAlreadyTakenException;
import view.View;
import view.ViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private final List<Map<String, String>> notes = new ArrayList<>();

    public boolean addNotes(HashMap<String, String> newNote) throws LoginAlreadyTakenException {

        for (Map<String, String> note : notes) {
            if (note.get(View.bundle.getString(ViewInterface.LOGIN_FIELD))
                    .equals(newNote.get(View.bundle.getString(ViewInterface.LOGIN_FIELD)))) {
                throw new LoginAlreadyTakenException(View.bundle
                        .getString(ViewInterface.LOGIN_EXCEPTION_MESSAGE),
                        newNote.get(View.bundle.getString(ViewInterface.LOGIN_FIELD)));
            }

        }

        this.notes.add(newNote);
        return true;
    }

    public List<Map<String, String>> getNotes() {
        return notes;
    }

}
