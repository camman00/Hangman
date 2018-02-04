package hang.man;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Hangman {
	private JFrame jFrame;
	private File dictionary;
	public static int playTimes;
	public ArrayList<String> words = new ArrayList<String>();
	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		try {
			hangman.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("The game crashed due to an IOException!");
		}
	}
	public Hangman() {
		jFrame = new JFrame();
		dictionary = getFile("dictionary.txt");
	}
	public void start() throws IOException {
		playTimes = Integer.valueOf(JOptionPane.showInputDialog("Hello, how many times would you like the play Hangman?"));
		words = readDictionary();
		
	}
	private File getFile(String name) {
		int fileLength = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString().length();
		String filePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(5, fileLength - 4);
		filePath += "src/" + name;
		return new File(filePath);
	}
	private ArrayList<String> readDictionary() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(dictionary));
		int[] integersToRead = new int[playTimes];
		for (int i = 0; i < playTimes; i++) {
			integersToRead[i] = new Random().nextInt(2999);
		}
		Arrays.sort(integersToRead);
		ArrayList<String> randomWords = new ArrayList<String>();
		
		for (int i = 0; i < integersToRead.length; i++) {
			bufferedReader.skip(integersToRead[i] - 1);
			randomWords.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		return randomWords;
	}
}
