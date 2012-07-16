package it.imolinfo.mobile.abrakanban.dto;

import java.util.ArrayList;
import java.util.List;


public class FolderListDTO 
{
    private Folders root = new Folders();

    public Folders getRoot() {
        return root;
    }
    
    public static class Folders
    {
        private List<FolderDTO> folders = new ArrayList<FolderDTO>();

        public List<FolderDTO> getFolders() {
            return folders;
        }
    }
}
