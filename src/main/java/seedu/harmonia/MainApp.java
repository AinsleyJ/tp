package seedu.harmonia;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.harmonia.commons.core.Config;
import seedu.harmonia.commons.core.LogsCenter;
import seedu.harmonia.commons.core.Version;
import seedu.harmonia.commons.exceptions.DataConversionException;
import seedu.harmonia.commons.util.ConfigUtil;
import seedu.harmonia.commons.util.StringUtil;
import seedu.harmonia.logic.Logic;
import seedu.harmonia.logic.LogicManager;
import seedu.harmonia.model.Model;
import seedu.harmonia.model.ModelManager;
import seedu.harmonia.model.ReadOnlyTaskList;
import seedu.harmonia.model.ReadOnlyUserPrefs;
import seedu.harmonia.model.TaskList;
import seedu.harmonia.model.UserPrefs;
import seedu.harmonia.model.util.SampleDataUtil;
import seedu.harmonia.storage.JsonTaskListStorage;
import seedu.harmonia.storage.JsonUserPrefsStorage;
import seedu.harmonia.storage.Storage;
import seedu.harmonia.storage.StorageManager;
import seedu.harmonia.storage.TaskListStorage;
import seedu.harmonia.storage.UserPrefsStorage;
import seedu.harmonia.ui.Ui;
import seedu.harmonia.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing TaskList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TaskListStorage taskListStorage = new JsonTaskListStorage(userPrefs.getTaskListFilePath());
        storage = new StorageManager(taskListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s task list and {@code userPrefs}. <br>
     * The data from the sample task list will be used instead if {@code storage}'s task list is not found,
     * or an empty task list will be used instead if errors occur when reading {@code storage}'s task list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTaskList> taskListOptional;
        ReadOnlyTaskList initialData;
        try {
            taskListOptional = storage.readTaskList();
            if (!taskListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample TaskList");
            }
            initialData = taskListOptional.orElseGet(SampleDataUtil::getSampleTaskList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty TaskList");
            initialData = new TaskList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TaskList");
            initialData = new TaskList();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TaskList");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Harmonia " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Harmonia ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
