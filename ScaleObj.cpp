/* Akshay Jaitly Cs 635 Programming Assignment
Steps to run in C++:
g++ ScaleObj.cpp -o ScaleObj
./Scaleobj Sample.obj 1 2 9
output Filename: 3dScaled.obj */

#include <iostream>
#include <fstream>
#include <string>
#include <stdlib.h>
#include <stdio.h>
#include <sstream>
#include <vector>

using namespace std;

int main(int argc , char *argv[]){
    if(argc != 5){
        cout << "Wrong no. of input arguments. Provide inputs in order: filename.obj x y z";
        return 1;
    }
    
    float x_scale;//Scaling factor for x
    float y_scale;//Scaling factor for y
    float z_scale;//Scaling factor for z
    
    // Declaring arguments
    string inputfile = argv[1];
    x_scale = atof(argv[2]);//Float conversion for x
    y_scale = atof(argv[3]);//Float conversion for y
    z_scale = atof(argv[4]);//Float conversion for z
    
    // Enter non 0 scaling factors
    if(x_scale <= 0 || y_scale <= 0 || z_scale <= 0){
        cout << "Error! Enter values greater than 0";
        return 1;
    }

	// Reading from file
	string line;
    // User's input file
	std::ifstream file(inputfile.c_str());
    // Generating Scaled output .obj file
	std::ofstream outputfile("3dScaled.obj");
    cout << "Generating Output file : 3dScaled.obj " << endl;
    
    std::string str;
    if(!file.is_open()){
        cout << "Can't open input file";// Error opening file
        return 1;
    }
    while (std::getline(file, str))
    {
        string output = str;
        // Parse the line to check if it starts with "v"
        string arr[4];
        int i = 0;
        stringstream ssin(str);
        while (ssin.good() && i < 4){
            ssin >> arr[i];
            ++i;
        }
        if(arr[0] == "v"){
            float x_axis = atof(arr[1].c_str());
            x_axis *= x_scale;//X axis
            float y_axis = atof(arr[2].c_str());
            y_axis *= y_scale;//Y axis
            float z_axis = atof(arr[3].c_str());
            z_axis *= z_scale;//Z axis
            
            // Copies contents in x axis string buffer
            std::ostringstream x_buf;
            x_buf<<x_axis;
            string tmp = arr[0] + " " + x_buf.str() + " ";
            // Copies contents in y axis string buffer
            std::ostringstream y_buf;
            y_buf << y_axis;
            tmp += y_buf.str() + " ";
            // Copies contents in z axis string buffer
            std::ostringstream z_buf;
            z_buf << z_axis;
            tmp += z_buf.str();
            // String concatenation
            output = tmp;
        }
        // Skip the surface normals if any
        if(arr[0] == "vn"){
            continue;
        }
        // Skip the surface tangents if any
        if(arr[0] == "vt"){
            continue;
        }
        
        // Generating output
        outputfile << output;
        outputfile << "\n";
    }

	return 0;
}
