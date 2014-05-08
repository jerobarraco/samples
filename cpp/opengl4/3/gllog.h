#ifndef GLLOG_H
#define GLLOG_H
#include <time.h>
#include <stdarg.h>
#include <stdio.h>
#define GL_LOG_FILE "gl.log"

bool restart_gl_log ();
bool gl_log (const char* message, ...) ;
bool gl_log_err (const char* message, ...);
bool gl_log2 (bool error, const char* message, ...);

class GLLog
{
public:
	GLLog();
};

#endif // GLLOG_H
