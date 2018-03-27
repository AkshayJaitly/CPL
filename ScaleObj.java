/* Akshay Jaitly Cs 635 Programming Assignment
Steps to run program in Java:
javac ScaleObj.java
java ScaleObj Sample.obj 2 1 16
output Filename:Scale.obj */
import java.io.*;

public class ScaleObj {
	public static void main(String[] args){
		if(args.length != 4){
			System.err.println("Wrong no. of input arguments, Inputs must be : filename.obj x y z");
			return;
		}
		
		// Declare arguemnts
		String filename = args[0];//arguement for filename
		float x_scale;//Scaling factor for x
		float y_scale;//Scaling factor for y
		float z_scale;//Scaling factor for z
		try{
			x_scale = Float.parseFloat(args[1]);//Float conversion
			y_scale = Float.parseFloat(args[2]);//Float conversion
			z_scale = Float.parseFloat(args[3]);//Float conversion
		}catch(Exception e){
			System.err.println("Error parsing the user inputted scaling factors");
			return;
		}

		String line = null;
        
		try{
			// Creating output file from an input file
			File f = new File(filename);
            String outputfile = "Scale.obj" ;//Scaled Output file
			System.out.println("Generating Output File : " + outputfile);
            
			// Reading from input file
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// Writing to output file
			FileWriter fileWriter = new FileWriter(outputfile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);			
			
			while((line = bufferedReader.readLine()) != null){
				// Output line to write into output file
				String output = line;
				
				String[] splitline = line.split("\\s+");
				if("v".equalsIgnoreCase(splitline[0])){
					if(splitline.length != 4){
						System.err.println("Fault! Obj file contains vertices with more than 3 coordinates...");
						System.err.println(line);						
					}
					
					// x axis
					float x_axis = Float.parseFloat(splitline[1]);
					x_axis *= x_scale;
					// y axis
					float y_axis = Float.parseFloat(splitline[2]);
					y_axis *= y_scale;
					// z axis
					float z_axis = Float.parseFloat(splitline[3]);
					z_axis *= z_scale;
					
                    // Output using string concatenation
                    output = splitline[0] + " " + Float.toString(x_axis);
                    output += " " + Float.toString(y_axis);
                    output += " " + Float.toString(z_axis);
				}
				
                // Ignore surface normals if any
				if("vn".equalsIgnoreCase(splitline[0])){
					continue;
				}
				
                // Ignore surface tangents if any
                if("vt".equalsIgnoreCase(splitline[0])){
                    continue;
                }
                
				bufferedWriter.write(output);
				bufferedWriter.newLine();
			}
			
			bufferedReader.close();
			bufferedWriter.close();
		}catch(FileNotFoundException ex){
			System.err.println("Cannot open file '" + filename + "'");
		}catch(IOException e){
			System.err.println("Error! Can't read file '" + filename + "'");
		}
		
		System.out.println("A scaled Wavefront .obj file is created!");
	}

}
