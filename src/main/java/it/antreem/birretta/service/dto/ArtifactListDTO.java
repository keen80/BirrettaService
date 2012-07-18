package it.antreem.birretta.service.dto;

import java.util.ArrayList;
import java.util.List;

public class ArtifactListDTO 
{
    private Artifacts root = new Artifacts();

    public Artifacts getRoot() {
        return root;
    }
    
    public static class Artifacts
    {
        private List<ArtifactDTO> epics = new ArrayList<ArtifactDTO>();
        private List<ArtifactDTO> stories = new ArrayList<ArtifactDTO>();
        private List<ArtifactDTO> tasks = new ArrayList<ArtifactDTO>();
        private List<ArtifactDTO> defects = new ArrayList<ArtifactDTO>();
        private List<ArtifactDTO> tests = new ArrayList<ArtifactDTO>();

        public List<ArtifactDTO> getDefects() {
            return defects;
        }

        public List<ArtifactDTO> getEpics() {
            return epics;
        }

        public List<ArtifactDTO> getStories() {
            return stories;
        }

        public List<ArtifactDTO> getTasks() {
            return tasks;
        }

        public List<ArtifactDTO> getTests() {
            return tests;
        }
    
    }
}
