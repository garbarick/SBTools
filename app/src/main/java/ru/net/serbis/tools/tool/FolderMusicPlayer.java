package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class FolderMusicPlayer extends Tool
{
    public FolderMusicPlayer()
    {
        super(
            R.layout.tool_folder_music_player,
            R.id.folder_music_player,
            R.id.folder_music_player_set
        );
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.folder_music_player:
                UITool.get().notImplementedYet(context);
                break;

            case R.id.folder_music_player_set:
                UITool.get().notImplementedYet(context);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.folder_music_player;
    }
}
