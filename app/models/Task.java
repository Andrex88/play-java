package models;

/**
 * Created by cristian.palacio on 28/09/2015.
 */
import play.data.validation.Constraints;

import java.util.*;

public class Task {

    public Long id;
    @Constraints.Required
    public String label;

    public static List<Task> all() {
        return new ArrayList<Task>();
    }

    public static void create(Task task) {
    }

    public static void delete(Long id) {
    }
}