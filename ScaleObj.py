"""
    Created on Fri Nov 17 22:34:05 2017
    
    @author: akshayjaitly
    """
import sys

# Inputs must be in the form filename.obj x y z
# Steps to run : python ScaleObj.py mini.obj 2 3 2
if (len(sys.argv) != 5):
    print("Incorrect no. of input arguments, Inputs must be : filename x y z")

# Declaring arguements
infile = sys.argv[1]
x_scale = sys.argv[2]
y_scale = sys.argv[3]
z_scale = sys.argv[4]

# Parse the x,y,z to float
x_scale = float(x_scale)
y_scale = float(y_scale)
z_scale = float(z_scale)

# Declaring input and output paths
file_in = open(infile, "r")
file_out = open("Scale"+ infile, "w")
# Read all lines in file using readline
for s in file_in.readlines():
    output = s
    tmp = s.split() # Split string into list
    if(len(tmp) > 0 and tmp[0] == "v" ):
        if(len(tmp) != 4):
            print("Fault! .obj file has vertices with more than 3 axes ..." + tmp)
            continue
            
        # x axis
        x_axis = float(tmp[1])
        x_axis *= x_scale
        # y axis
        y_axis = float(tmp[2])
        y_axis *= y_scale
        # z axis
        z_axis = float(tmp[3])
        z_axis *= z_scale
        
        # Output which is to be displayed using string concatenation
        output = tmp[0] + " " + str(x_axis) + " " + str(y_axis) + " " + str(z_axis) + "\n"

    # Surface normals are skipped
    if(len(tmp) > 0 and tmp[0] == "vn"):
        continue

    # Surface tangents are skipped
    if(len(tmp) > 0 and tmp[0] == "vt"):
      continue

    # Writing to output
    file_out.write(output)
