package it.imolinfo.mobile.abrakanban.dto;

/**
 * POJO che rappresenta un'attivit&agrave; base da svolgere.
 */
public class ArtifactDTO 
{
    private String id;
    private String title;
    private String description;
    private String status;
    private int priority;
    private String category;
    private String kind;
    private String planningFolder;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPlanningFolder() {
        return planningFolder;
    }

    public void setPlanningFolder(String planningFolder) {
        this.planningFolder = planningFolder;
    }
}
