///http://www.antongerdelan.net/opengl/glcontext2.html
#define GLFW_DLL
#include <GL/glew.h>
#include <GLFW/glfw3.h> //We NEED glfw3 THREE! so we NEED to compile it ourselves
#include <stdio.h>
#include "gllog.h"

void glfw_error_callback (int error, const char* description) {
	gl_log2(true, "GLFW ERROR: code %i msg: %s\n", error, description);
}

GLFWwindow* initCrap(){
	if(!restart_gl_log ()) return NULL;
	// start GL context and O/S window using the GLFW helper library
	gl_log2 (false, "starting GLFW\n%s\n", glfwGetVersionString ());
	// register the error call-back function that we wrote, above
	glfwSetErrorCallback (glfw_error_callback);
	// start GL context and O/S window using the GLFW helper library
  if (!glfwInit ()) {
		gl_log2(true, "ERROR: could not start GLFW3\n");
    return NULL;
  }

    // uncomment these lines if on Apple OS X
  /*glfwWindowHint (GLFW_CONTEXT_VERSION_MAJOR, 3);
  glfwWindowHint (GLFW_CONTEXT_VERSION_MINOR, 2);
  glfwWindowHint (GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
  glfwWindowHint (GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);*/

  GLFWwindow* window = glfwCreateWindow (640, 480, "Hello Triangle", NULL, NULL);
  if (!window) {
    fprintf (stderr, "ERROR: could not open window with GLFW3\n");
    glfwTerminate();
    return NULL;
  }
  glfwMakeContextCurrent (window);

  // start GLEW extension handler
  glewExperimental = GL_TRUE;
  glewInit ();

  // get version info
  const GLubyte* renderer = glGetString (GL_RENDERER); // get renderer string
  const GLubyte* version = glGetString (GL_VERSION); // version as a string
  printf ("Renderer: %s\n", renderer);
  printf ("OpenGL version supported %s\n", version);

  // tell GL to only draw onto a pixel if the shape is closer to the viewer
  glEnable (GL_DEPTH_TEST); // enable depth-testing
  glDepthFunc (GL_LESS); // depth-testing interprets a smaller value as "closer"
	return window;//bitches!
}

void deInitCrap(){
	// close GL context and any other GLFW resources
  glfwTerminate();
}

void doStuff(GLFWwindow* window){
	// // // Define a Triangle in a Vertex Buffer

	// Okay, let's define a triangle from 3 points. Later, we can look at doing transformations and perspective,
	// but for now let's draw it flat onto the final screen area; x between -1 and 1, y between -1 and 1, and z = 0.
	// We will pack all of these points into a big array of floating-point numbers; 9 in total.
	// We will start with the top point, and proceed clock-wise in order: xyzxyzxyz.
	// The order should always be in the same winding direction, so that we can later determine which side is the front,
	// and which side is the back.

	float points[] = {
	   0.0f,  0.5f,  0.0f,
	   0.5f, -0.5f,  0.0f,
	  -0.5f, -0.5f,  0.0f
	};
	// We will copy this chunk of memory onto the graphics cards in a unit called a vertex buffer object (VBO). To do this we "generate" an empty buffer, set it as the current buffer in OpenGL's state machine by "binding", then copy the points into the currently bound buffer:

	GLuint vbo = 0;
	glGenBuffers (1, &vbo);
	glBindBuffer (GL_ARRAY_BUFFER, vbo);
	//The last line tells GL that the buffer is the size of 9 floating point numbers, and gives it the address of the first value.
	glBufferData (GL_ARRAY_BUFFER, 9 * sizeof (float), points, GL_STATIC_DRAW);

	//Now an unusual step. Most meshes will use a collection of vertex buffers; one for vertex points, one for texture-coordinates, one for vertex normals, etc. In older GL implementations we would have to bind each one, and define their memory layout, every time that we draw the mesh. To simplify that, we have new thing called the vertex attribute object (VAO), which remembers all of the vertex buffers that you want to use, and the memory layout of each one. We set up the vertex array object once per mesh. When we want to draw, all we do then is bind the VAO and draw.
	//Here we tell GL to generate a new VAO for us. It sets an unsigned integer to identify it with later. We bind it, to bring it in to focus in the state machine. This lets us enable the first attribute; 0. We are only using a single vertex buffer, so we know that it will be attribute location 0. The glVertexAttribPointer function defines the layout of our first vertex buffer; "0" means define the layout for attribute number 0. "3" means that the variables are vec3 made from every 3 floats (GL_FLOAT) in the buffer.
	GLuint vao = 0;
	glGenVertexArrays (1, &vao);
	glBindVertexArray (vao);
	glEnableVertexAttribArray (0);
	glBindBuffer (GL_ARRAY_BUFFER, vbo);
	glVertexAttribPointer (0, 3, GL_FLOAT, GL_FALSE, 0, NULL);

	//We need to use a shader programme, written in OpenGL Shader Language (GLSL), to define how to draw our shape from the vertex attribute object. You will see that the attribute pointer from the VAO will match up to our input variables in the shader.
	//This shader programme is made from the minimum 2 parts; a vertex shader, which describes where the 3d points should end up on the display, and a fragment shader which colours the surfaces. Both are be written in plain text, and look a lot like C programmes. Loading these from plain-text files would be nicer; I just wanted to save a bit of web real-estate by hard-coding them here.
	//The first line says which version of the shading language to use; in this case 4.0.0. If you're limited to OpenGL 3, change the first line from "400" to "150"; the version of the shading language compatible with OpenGL 3.2, or "330", for OpenGL 3.3. My vertex shader has 1 input variable; a vec3 (vector made from 3 floats), which matches up to our VAO's attribute pointer. This means that each vertex shader gets 3 of the 9 floats from our buffer - therefore 3 vertex shaders will run concurrently; each one positioning 1 of the vertices. The output has a reserved name gl_Position and expects a 4d float. You can see that I haven't modified this at all, just added a 1 to the 4th component. The 1 at the end just means "don't calculate any perspective".
	const char* vertex_shader =
			"#version 400\n"
			"in vec3 vp;"
			"void main () {"
			"  gl_Position = vec4 (vp, 1.0);"
			"}"
	;

	//Again, you may need to change the first line of the fragment shader if you are on OpenGL 3.2 or 3.3. My fragment shader will run once per pixel-sized fragment that the surface of the shape covers. We still haven't told GL that it will be a triangle (it could be 2 lines). You can guess that for a triangle, we will have lots more fragment shaders running than vertex shaders for this shape. The fragment shader has one job - setting the colour of each fragment. It therefore has 1 output - a 4d vector representing a colour made from red, blue, green, and alpha components - each component has a value between 0 and 1. We aren't using the alpha component. Can you guess what colour this is?
	const char* fragment_shader =
		"#version 400\n"
		"out vec4 frag_colour;"
		"void main () {"
		"  frag_colour = vec4 (0.5, 0.0, 0.5, 1.0);"
		"}"
	;
	//Before using the shaders we have to load the strings into a GL shader, and compile them.
	GLuint vs = glCreateShader (GL_VERTEX_SHADER);
	glShaderSource (vs, 1, &vertex_shader, NULL);
	glCompileShader (vs);
	GLuint fs = glCreateShader (GL_FRAGMENT_SHADER);
	glShaderSource (fs, 1, &fragment_shader, NULL);
	glCompileShader (fs);

	//Now, these compiled shaders must be combined into a single, executable GPU shader programme. We create an empty "program", attach the shaders, then link them together.
	GLuint shader_programme = glCreateProgram ();
	glAttachShader (shader_programme, fs);
	glAttachShader (shader_programme, vs);
	glLinkProgram (shader_programme);

	//////////////////
	//// Drawing ////
	////////////////

	// We draw in a loop. Each iteration draws the screen once; a "frame" of rendering. The loop finishes if the window is closed. Later we can also ask GLFW is the escape key has been pressed.
	while (!glfwWindowShouldClose (window)) {
	  // wipe the drawing surface clear
	  glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	  glUseProgram (shader_programme);
	  glBindVertexArray (vao);
	  // draw points 0-3 from the currently bound VAO with current in-use shader
	  glDrawArrays (GL_TRIANGLES, 0, 3);
	  // update other events like input handling
	  glfwPollEvents ();
	  // put the stuff we've been drawing onto the display
	  glfwSwapBuffers (window);
	}
	//First we clear the drawing surface, then set the shader programme that should be "in use" for all further drawing. We set our VAO (not the VBO) as the input variables that should be used for all further drawing (in our case just some vertex points). Then we can draw, and we want to draw in triangles mode (1 triangle for every 3 points), and draw from point number 0, for 3 points. GLFW3 requires that we manually call glfwPollEvents() to update things non-graphical events like key-presses. Finally, we flip the swap surface onto the screen, and the screen onto the next drawing surface (we have 2 drawing buffers). Done!
}

int main () {
	GLFWwindow* window= initCrap();
	if(!window){
		return -1;
	}
	doStuff(window);
	deInitCrap();
	return 0;
}
