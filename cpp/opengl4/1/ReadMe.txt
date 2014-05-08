http://www.antongerdelan.net/opengl/hellotriangle.html

Make sure that your library paths to GLFW and GLEW are correct. I copied the include folders from GLEW and GLFW into the same folder as my project, so my include folder contains a folder called GLFW, and a folder called GL. I am using MinGW and compile with this line:

 g++ -o hellot.exe main.cpp glfw3dll.a libglew32.dll.a -I include -L./ -lglew32 -lglfw3 -lopengl32

I put the GLFW and GLEW library files in my local folder, and told it to look there for the dynamic libraries with -L ./. It should find the opengl32 library on the system path. On Linux you'll most likely install via repositories, and you won't need the -I or -L path bits. You also won't need the glfw3dll.a and libglew32.dll.a files. The OpenGL library will be called libGL. On Linux I have this command:
 g++ -o hellot main.cpp -lglfw -lGL -lGLEW



Nande:
I dont really remember but you do not need to do the previous stuff if you install glew and glfw form your system including the dev stuff
Modify the .pro file to add this libraries
INCLUDEPATH += ../glfw-3.0.4/include

unix:!macx: LIBS += -L$$PWD/../glfw-3.0.4/src/ -lglfw3 -lGL -lGLEW -lX11 -lXxf86vm -lpthread  -lXrandr -lXi
INCLUDEPATH += $$PWD/../glfw-3.0.4/include
DEPENDPATH += $$PWD/../glfw-3.0.4/include

(some can be included using the add->library menu on qtcreator)

Also you need to install the following libraries into your system using your package manager:
libxi-dev
libxrandr-dev
libxxf86vm-dev
libx11-dev
libglew-dev
Gl libs dev comes with the driver, for amd i've installed them manually and confirmed the option to add the developer stuff
