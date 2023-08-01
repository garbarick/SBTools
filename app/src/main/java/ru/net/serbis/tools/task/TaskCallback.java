package ru.net.serbis.tools.task;

import ru.net.serbis.tools.data.*;

public interface TaskCallback<R>
{
    void progress(int progress);
    void onResult(R result, TaskError error);
}
