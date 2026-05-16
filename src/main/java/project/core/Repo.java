package project.core;

public interface Repo {
    void SaveData(StudentSystem sys);
    void SaveReport(StudentSystem sys);
    void LoadData(StudentSystem sys);
    void Close();
}