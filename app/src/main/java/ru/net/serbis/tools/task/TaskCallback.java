package ru.net.serbis.tools.task;

import ru.net.serbis.tools.data.*;

public interface TaskCallback
{
    void progress(int progress);
    void onResult(boolean result, TaskError error);
}
