package hang.man;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman {
	private JFrame jFrame;
	private JPanel jPanel;
	private File dictionary;
	public static int playTimes;
	private boolean completeMethod = true;
	public ArrayList<String> words = new ArrayList<String>();
	public Stack<String> wordsStack = new Stack<String>();
	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		try {
			hangman.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Closing the game!");
		}
	}
	public Hangman() {
		jFrame = new JFrame();
		dictionary = getFile("dictionary.txt");
	}
	public void start() throws IOException {
		playTimes = Integer.valueOf(JOptionPane.showInputDialog("Hello, how many times would you like the play Hangman?"));
		if(playTimes > 2999 || playTimes <= 0)
			return;
		words = readDictionary();
		putToStack();
		jPanel = new Content();
		jFrame.add(jPanel);
		jFrame.setSize(600, 800);
		jFrame.setVisible(true);
		
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
		ArrayList<String> contents = allContents(bufferedReader);
		for(int i : integersToRead) {
			randomWords.add(contents.get(i - 1));
		}
		bufferedReader.close();
		return randomWords;
	}
	private ArrayList<String> allContents(BufferedReader bufferedReader) throws IOException {
		if(completeMethod == false)
			return null;
		ArrayList<String> contents = new ArrayList<String>();
		String line;
		while((line = bufferedReader.readLine()) != null) {
			contents.add(line);
		}
		completeMethod = false;
		return contents;
	}
	private void putToStack() {
		while(words.isEmpty() != true) {
			String s = words.get(new Random().nextInt(words.size()));
			wordsStack.push(s);
			words.remove(s);
		}
	}

}
