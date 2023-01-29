import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.NoSuchElementException;

public class Project1{
	public static void main(String[] args) throws IOException {
		// redirect standart output to outputFile
		System.setOut(new PrintStream(new FileOutputStream(args[1])));
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		

		// compile with following command
		// javac src/*.java -d bin 

		// run with 
		// cd bin && java Project1 ../input.txt ../myout.txt  && cd ..

		// basic command line commands
		// - ls : list files in current directory
		// - cd : change directory


		// read the first line of the file
		String line = reader.readLine();

		Factory factory = new FactoryImpl();
		while(line!=null){
			
			String[] tokens = line.split(" ");

			
			String command = tokens[0];
			switch(command) {
				case "AF":
				

					int id = Integer.parseInt(tokens[1]);
					int value = Integer.parseInt(tokens[2]);

					Product product = new Product(id, value);
					factory.addFirst(product);
					

					break;	
				case "AL":

					int id1 = Integer.parseInt(tokens[1]);
					int value1 = Integer.parseInt(tokens[2]);
					//create product object
					Product product1 = new Product(id1, value1);
					factory.addLast(product1);
					
					break;
			
			    case "A":
					int index2 = Integer.parseInt(tokens[1]);
					int id2 = Integer.parseInt(tokens[2]);
					int value2 = Integer.parseInt(tokens[3]);
					try {


						Product product2 = new Product(id2, value2);
						factory.add(index2, product2);
						break;
					} catch (IndexOutOfBoundsException e){
						System.out.println(e.getMessage());
					}
					break;

				case "RF":

					try {
						System.out.println(factory.removeFirst());
					} catch (NoSuchElementException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				
					
					break;
				case "RL":

					try {
						System.out.println(factory.removeLast());
					} catch (NoSuchElementException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;
				case "RI":
					

					int index3 = Integer.parseInt(tokens[1]);

					try {
						System.out.println(factory.removeIndex(index3));
					} catch (IndexOutOfBoundsException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;
				case "RP":
					int  value4= Integer.parseInt(tokens[1]);

					try {
						System.out.println(factory.removeProduct(value4));
					} catch (NoSuchElementException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "F":
					int value5 = Integer.parseInt(tokens[1]);

					try {
						System.out.println(factory.find(value5));
					} catch (NoSuchElementException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "G":
					int index4 = Integer.parseInt(tokens[1]);

					try {

						System.out.println(factory.get(index4));
					} catch (IndexOutOfBoundsException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "U":
					int index5 = Integer.parseInt(tokens[1]);
					int value6 = Integer.parseInt(tokens[2]);

					try {
						System.out.println(factory.update(index5, value6));
					} catch (NoSuchElementException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}

					break;
				case "FD":
					
					System.out.println(factory.filterDuplicates());
					break;
				case "R":
					factory.reverse();
					System.out.println(factory.toString());
					break;
				case "P":
					System.out.println(factory.toString());
					break;

		
		



			

			// read the next line
			
		}
		line = reader.readLine();
		
	} 
	reader.close();
}
}

