package seedu.harmonia.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.harmonia.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.harmonia.commons.exceptions.IllegalValueException;
import seedu.harmonia.commons.util.JsonUtil;
//import seedu.harmonia.model.TaskList;
//import seedu.harmonia.testutil.TypicalTasks;

public class JsonSerializableTaskListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskListTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTaskList.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskList.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTaskList.json");

    //    @Test
    //    public void toModelType_typicalTasksFile_success() throws Exception {
    //        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
    //                JsonSerializableTaskList.class).get();
    //        TaskList taskListFromFile = dataFromFile.toModelType();
    //        TaskList typicalTaskList = TypicalTasks.getTypicalTaskList();
    //        assertEquals(taskListFromFile, typicalTaskList);
    //    }

    @Test
    public void toModelType_invalidTasksFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskList.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

}
