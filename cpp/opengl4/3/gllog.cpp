#include "gllog.h"

GLLog::GLLog()
{
}


/// Starting a Log File

//Debugging graphical programmes is a huge pain. They don't have functions to print text to the screen any more, and having a console open on the side printing things out can quickly get overwhelming. I strongly suggest starting a "GL log" straight away, so you can load it up to check out what specifications a user's system has, and also debug any problems after the programme has finished.

//The actual structure of your log functions will depend on your preferences. I prefer C fprintf() to C++ output streams so I'm going to make something that takes a variable number of arguments like printf() does. You might prefer to stream variables out, cout style. To make functions that take a variable number of arguments; #include <stdarg.h>.
bool restart_gl_log () {
  FILE* file = fopen (GL_LOG_FILE, "w");
  if (!file) {
    fprintf (
      stderr,
      "ERROR: could not open GL_LOG_FILE log file %s for writing\n",
      GL_LOG_FILE
    );
    return false;
  }
  time_t now = time (NULL);
  char* date = ctime (&now);
  fprintf (file, "GL_LOG_FILE log. local time %s\n", date);
  fclose (file);
  return true;
}

//This first function just opens the log file and prints the date and time at the top - always handy. It might make sense to print the version number of your code here too. In GCC this would be the built-in strings __DATE__ and __TIME__. Note that after printing to the log file we close it again rather than keep it open.
//This function is the main log print-out. The "..." parameter is part of C's variable arguments format, and lets us give it any number of parameters, which will be mapped to corresponding string formatting in the message string, just like printf(). We open the file in "a[ppend]" mode, which means adding a line to the existing end of the file, which is what we want, because we just closed it again since we wrote the time at the top. C has a rather funny-looking start and end function for processing the variable arguments. After writing to the file, we close it again. Why? Because if the programme crashes we don't lose our log - the last appended message can be very enlightening.
bool gl_log (const char* message, ...) {
  va_list argptr;
  FILE* file = fopen (GL_LOG_FILE, "a");
  if (!file) {
    fprintf (
      stderr,
      "ERROR: could not open GL_LOG_FILE %s file for appending\n",
      GL_LOG_FILE
    );
    return false;
  }
  va_start (argptr, message);
  vfprintf (file, message, argptr);
  va_end (argptr);
  fclose (file);
  return true;
}
bool gl_log_err (const char* message, ...) {
  va_list argptr;
  FILE* file = fopen (GL_LOG_FILE, "a");
  if (!file) {
    fprintf (
      stderr,
      "ERROR: could not open GL_LOG_FILE %s file for appending\n",
      GL_LOG_FILE
    );
    return false;
  }
  va_start (argptr, message);
  vfprintf (file, message, argptr);
  va_end (argptr);
  va_start (argptr, message);
  vfprintf (stderr, message, argptr);
  va_end (argptr);
  fclose (file);
  return true;
}

bool gl_log2 (bool error, const char* message, ...) {
  va_list argptr;
  FILE* file = fopen (GL_LOG_FILE, "a");
  if (!file) {
    fprintf (
      stderr,
      "ERROR: could not open GL_LOG_FILE %s file for appending\n",
      GL_LOG_FILE
    );
    return false;
  }
  va_start (argptr, message);
  vfprintf (file, message, argptr);
	if (error)
		vfprintf (stderr, message, argptr);
  va_end (argptr);
  fclose (file);
  return true;
}
