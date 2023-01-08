package course_eLearning.course_eLearning.service;


import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.model.Module;

public interface ModuleService  {
    public abstract Module createLesson(String moduleId, Lesson lesson);

    public abstract Module getModuleById(String moduleId);
}
