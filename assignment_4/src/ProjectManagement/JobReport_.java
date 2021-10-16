package ProjectManagement;

interface JobReport_ {

   default String user() { return null; }

   default String project_name()  { return null; }

   default int budget()  { return 0; }

   default int arrival_time()  { return 0; }

   default int completion_time() { return 0; }

}